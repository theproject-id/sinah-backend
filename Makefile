.DEFAULT_GOAL := help

COMPOSE_PATH=./compose.yml
MIGRATION_PATH=./src/main/resources/db/migrations

IMAGE_VERSION := $(shell git rev-parse --short HEAD)
IMAGE_NAME=ghcr.io/theproject-id/sinah-backend

# Colors
RED     := $(shell tput -Txterm setaf 1)
GREEN   := $(shell tput -Txterm setaf 2)
BLUE    := $(shell tput -Txterm setaf 4)
CYAN    := $(shell tput -Txterm setaf 6)
RESET   := $(shell tput -Txterm sgr0)

## Application

run: docker-start ## Setup environment, start containers and run spring application
	@./mvnw spring-boot:run

test:
	@./mvnw clean test

## Build

image-build: ## Build the project image
	@docker build . -t $(IMAGE_NAME):$(IMAGE_VERSION)$(if $(IMAGE_PROFILE),-$(IMAGE_PROFILE))

image-publish: ## Publish the project image
	@docker push $(IMAGE_NAME):$(IMAGE_VERSION)$(if $(IMAGE_PROFILE),-$(IMAGE_PROFILE))

## Migration

migrate: migrate-validate migrate-run migrate-info ## Run validate + migrate + info
	@echo "${GREEN}Migration complete${RESET}"

migrate-new: ## Create a new versioned migration file. Usage: make migrate-new file=V1__init_schema.sql
ifndef file
	@echo "${RED}Error: file is undefined.${RESET} Usage: make migrate-new file=<migration_filename.sql>"
	@exit 1
endif
	@touch ${MIGRATION_PATH}/$(file)
	@echo "-- Migration ${MIGRATION_PATH}/$(file)" > ${MIGRATION_PATH}/$(file)
	@echo "${GREEN}Created migration ${MIGRATION_PATH}/$(file)${RESET}"

migrate-run: ## Run Flyway migrations
	@./mvnw flyway:migrate
	@echo "${GREEN}Migrations applied successfully.${RESET}"

migrate-validate: ## Validate migrations
	@./mvnw flyway:validate
	@echo "${GREEN}Validation completed.${RESET}"

migrate-clean: ## Clean database
	@./mvnw flyway:clean
	@echo "${GREEN}Database cleaned.${RESET}"

migrate-info: ## Show migration info
	@./mvnw flyway:info

## Docker

docker-start: ## Start the local containers.
	@docker compose -f ${COMPOSE_PATH} up -d

docker-stop: ## Stop the local containers.
	@docker compose -f ${COMPOSE_PATH} stop

docker-down: ## Remove the local containers.
	@docker compose -f ${COMPOSE_PATH} down

docker-reset: ## Stop, delete, build and start the local containers.
	@docker compose -f ${COMPOSE_PATH} stop
	@docker compose -f ${COMPOSE_PATH} rm
	@docker compose -f ${COMPOSE_PATH} up -d

## Extra

# https://gist.github.com/thomaspoignant/5b72d579bd5f311904d973652180c705
help: ## Show this help.
	@echo
	@echo 'Usage:'
	@echo '  ${CYAN}make${RESET} ${GREEN}<target>${RESET}'
	@echo
	@echo 'Targets:'
	@awk 'BEGIN {FS = ":.*?## "} { \
		if (/[a-zA-Z_\-]+:.*?##.*$$/) {printf "    ${CYAN}%-25s${GREEN}%s${RESET}\n", $$1, $$2} \
		else if (/^## .*$$/) {printf "  ${BLUE}%s${RESET}\n", substr($$1,4)} \
		}' $(MAKEFILE_LIST)

# Load .env file into Make
ifneq (,$(wildcard .env))
  include .env
  export $(shell sed 's/=.*//' .env)
endif