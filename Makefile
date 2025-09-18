# Makefile
.DEFAULT_GOAL := help

COMPOSE_PATH=./compose.yml

# Colors
RED     := $(shell tput -Txterm setaf 1)
GREEN   := $(shell tput -Txterm setaf 2)
BLUE    := $(shell tput -Txterm setaf 4)
CYAN    := $(shell tput -Txterm setaf 6)
RESET   := $(shell tput -Txterm sgr0)

## Application

run: setup-env docker-start ## Setup environment, start containers and run spring application
	@./mvnw spring-boot:run

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

setup-env: ## Loads .env file
	@if [ ! -f .env ]; then echo "${RED}❌ Error: .env file not found. Please create one before running."; exit 1; fi
	@export $(cat .env | xargs) >/dev/null 2>&1

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