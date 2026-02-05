# Azure Deployment Guide

This guide walks through deploying the landing-backend application to Azure Container Apps with PostgreSQL database.

## Prerequisites

- Azure CLI installed and configured (`az login`)
- Docker installed and running
- Gradle installed (or use the Gradle wrapper)
- Appropriate Azure permissions to create resources

---

## 1. Configure Environment Variables

Set up environment variables for Azure resource names and configuration. These variables will be used throughout the deployment process.

```bash
export AZ_PROJECT_INC="azure-deploy-inclusive" 
export AZ_RESOURCE_GROUP="rg${AZ_PROJECT_INC}"
export AZ_LOCATION="westus"
export AZ_CONTAINERAPP="ca${AZ_PROJECT_INC}"
export AZ_CONTAINERAPP_ENV="cae${AZ_PROJECT_INC}"
export AZ_POSTGRES_DB_NAME="landingdb${AZ_PROJECT_INC}"
export AZ_POSTGRES_USERNAME="postgres"
export AZ_POSTGRES_PASSWORD="example"
export AZ_POSTGRES_SERVER_NAME="psql${AZ_PROJECT_INC}"
```

**Note:** Replace `"example"` with a strong password for production deployments.

---

## 2. Create Azure Resource Group

Create a resource group to contain all Azure resources for this deployment. A resource group is a logical container for managing related Azure resources together.

```bash
az group create --name $AZ_RESOURCE_GROUP --location $AZ_LOCATION
```

---

## 3. Register PostgreSQL Provider

Register the PostgreSQL Flexible Server provider with your Azure subscription. This enables you to create PostgreSQL database resources.

```bash
az provider register --namespace Microsoft.DBforPostgreSQL
```

**Note:** This registration may take a few minutes. You can check the status with:
```bash
az provider show --namespace Microsoft.DBforPostgreSQL --query "registrationState"
```

---

## 4. Create PostgreSQL Database Server

**Note:** The original file was missing this step. Create the PostgreSQL Flexible Server before retrieving the connection string.

```bash
az postgres flexible-server create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_POSTGRES_SERVER_NAME \
    --location $AZ_LOCATION \
    --admin-user $AZ_POSTGRES_USERNAME \
    --admin-password $AZ_POSTGRES_PASSWORD \
    --sku-name Standard_B1ms \
    --tier Burstable \
    --public-access 0.0.0.0 \
    --storage-size 32 \
    --version 14
```

Create the database:

```bash
az postgres flexible-server db create \
    --resource-group $AZ_RESOURCE_GROUP \
    --server-name $AZ_POSTGRES_SERVER_NAME \
    --database-name $AZ_POSTGRES_DB_NAME
```

---

## 5. Retrieve PostgreSQL Connection String

Retrieve the JDBC connection string for the PostgreSQL database. This will be used to configure the application's database connection.

```bash
export POSTGRES_CONNECTION_STRING=$(
    az postgres flexible-server show-connection-string \
    --server-name "$AZ_POSTGRES_SERVER_NAME" \
    --database-name "$AZ_POSTGRES_DB_NAME" \
    --admin-user "$AZ_POSTGRES_USERNAME" \
    --admin-password "$AZ_POSTGRES_PASSWORD" \
    --query "connectionStrings.jdbc" \
    --output tsv
)
```

Add SSL parameter to the connection string for secure database connections:

```bash
export POSTGRES_CONNECTION_STRING_SSL="$POSTGRES_CONNECTION_STRING&ssl=true"
```

Display the connection string for verification:

```bash
echo "POSTGRES_CONNECTION_STRING_SSL=$POSTGRES_CONNECTION_STRING_SSL"
```

---

## 6. Configure Application Database Connection

Update `src/main/resources/application.yaml` with the database connection details. Use the connection string retrieved in the previous step.

**Example configuration:**
```yaml
datasource:
    url: jdbc:postgresql://psqlazure-deploy-inclusive.postgres.database.azure.com:5432/landingdbazure-deploy-inclusive
    username: postgres
    password: example
    driver-class-name: org.postgresql.Driver
```

**Note:** Replace the example values with your actual database server name, database name, username, and password. For production, use environment variables or Azure Key Vault for sensitive credentials.

---

## 7. Build the Application

Clean previous build artifacts and create a new JAR file using Gradle. The `bootJar` task packages the Spring Boot application into an executable JAR.

```bash
./gradlew clean bootJar
```

Ensure the Gradle wrapper is executable (if needed):

```bash
chmod +x gradlew
```

---

## 8. Build Docker Image

Build a Docker image from the Dockerfile in the current directory. The image will be tagged with the name `landing-backend` and version `0.0.1`.

```bash
docker build -t landing-backend:0.0.1 .
```

Verify the image was created successfully:

```bash
docker images
```

---

## 9. Test Docker Image Locally (Optional)

Test the Docker image locally before deploying to Azure. This helps verify the application works correctly in a containerized environment.

```bash
docker run -p 8080:8080 landing-backend:0.0.1
```

**Note:** Make sure the database connection in `application.yaml` is accessible from your local machine, or use environment variables to override the connection string.

---

## 10. Deploy to Azure Container Apps

Deploy the application to Azure Container Apps. The `az containerapp up` command builds and deploys the application from the current directory.

```bash
az containerapp up \
    --name "$AZ_CONTAINERAPP" \
    --environment "$AZ_CONTAINERAPP_ENV" \              
    --location "$AZ_LOCATION" \      
    --resource-group "$AZ_RESOURCE_GROUP" \              
    --ingress external \                         
    --target-port 8080 \                               
    --source .
```

**Parameters explained:**
- `--name`: Name of the container app
- `--environment`: Container Apps environment (created automatically if it doesn't exist)
- `--location`: Azure region for deployment
- `--resource-group`: Resource group containing the resources
- `--ingress external`: Enable external (public) access to the application
- `--target-port`: Port the application listens on inside the container
- `--source .`: Build and deploy from the current directory

---

## 11. Retrieve Application URL

After deployment, retrieve the fully qualified domain name (FQDN) of your deployed application. This is the public URL where your application is accessible.

```bash
export AZ_APP_URL=$(
    az containerapp show \     
        --name "$AZ_CONTAINERAPP" \                  
        --resource-group "$AZ_RESOURCE_GROUP" \
        --query "properties.configuration.ingress.fqdn" \
        --output tsv \                           
)    

echo "AZ_APP_URL=$AZ_APP_URL"
```

Visit the URL in your browser to verify the deployment.

---

## Updating the Deployment

When you make changes to the project and need to deploy an updated version, follow these steps:

### Option 1: Update Using `az containerapp up` (Recommended)

The simplest way to update your deployment is to use `az containerapp up` again. This command will rebuild and redeploy your application automatically.

**Steps:**

1. **Ensure environment variables are set** (if starting a new terminal session):
   ```bash
   export AZ_PROJECT_INC="azure-deploy-inclusive" 
   export AZ_RESOURCE_GROUP="rg${AZ_PROJECT_INC}"
   export AZ_LOCATION="westus"
   export AZ_CONTAINERAPP="ca${AZ_PROJECT_INC}"
   export AZ_CONTAINERAPP_ENV="cae${AZ_PROJECT_INC}"
   ```

2. **Rebuild and redeploy** (this will automatically build a new image and update the container app):
   ```bash
   az containerapp up \
       --name "$AZ_CONTAINERAPP" \
       --environment "$AZ_CONTAINERAPP_ENV" \              
       --location "$AZ_LOCATION" \      
       --resource-group "$AZ_RESOURCE_GROUP" \              
       --ingress external \                         
       --target-port 8080 \                               
       --source .
   ```

   **Note:** The `az containerapp up` command will:
   - Build a new Docker image from your updated source code
   - Push the image to Azure Container Registry (created automatically if needed)
   - Update the container app with the new image
   - Perform a rolling update (zero-downtime deployment)

### Option 2: Manual Update Process

If you prefer more control over the update process or want to use a specific version tag:

1. **Build the updated application:**
   ```bash
   ./gradlew clean bootJar
   ```

2. **Build a new Docker image with an updated version tag:**
   ```bash
   # Update the version number (e.g., 0.0.2, 0.0.3, etc.)
   docker build -t landing-backend:0.0.2 .
   ```

3. **Test the new image locally (optional but recommended):**
   ```bash
   docker run -p 8080:8080 landing-backend:0.0.2
   ```

4. **Update the container app with the new image:**

   If using Azure Container Registry (ACR):
   ```bash
   # Tag and push to ACR (replace with your ACR name)
   export ACR_NAME="acr${AZ_PROJECT_INC}"
   az acr login --name $ACR_NAME
   docker tag landing-backend:0.0.2 $ACR_NAME.azurecr.io/landing-backend:0.0.2
   docker push $ACR_NAME.azurecr.io/landing-backend:0.0.2
   
   # Update the container app
   az containerapp update \
       --name "$AZ_CONTAINERAPP" \
       --resource-group "$AZ_RESOURCE_GROUP" \
       --image $ACR_NAME.azurecr.io/landing-backend:0.0.2
   ```

   Or, if using `az containerapp up` with a specific image:
   ```bash
   az containerapp up \
       --name "$AZ_CONTAINERAPP" \
       --environment "$AZ_CONTAINERAPP_ENV" \              
       --location "$AZ_LOCATION" \      
       --resource-group "$AZ_RESOURCE_GROUP" \              
       --ingress external \                         
       --target-port 8080 \                               
       --source . \
       --image landing-backend:0.0.2
   ```

### Verify the Update

After updating, verify the new version is running:

```bash
# Check the container app status
az containerapp show \
    --name "$AZ_CONTAINERAPP" \
    --resource-group "$AZ_RESOURCE_GROUP" \
    --query "properties.template.containers[0].image"

# View recent revisions
az containerapp revision list \
    --name "$AZ_CONTAINERAPP" \
    --resource-group "$AZ_RESOURCE_GROUP" \
    --output table

# Check application logs
az containerapp logs show \
    --name "$AZ_CONTAINERAPP" \
    --resource-group "$AZ_RESOURCE_GROUP" \
    --follow
```

### Rollback to Previous Version

If you need to rollback to a previous version:

```bash
# List all revisions
az containerapp revision list \
    --name "$AZ_CONTAINERAPP" \
    --resource-group "$AZ_RESOURCE_GROUP" \
    --output table

# Activate a previous revision (replace REVISION_NAME with actual revision name)
az containerapp revision activate \
    --name "$AZ_CONTAINERAPP" \
    --resource-group "$AZ_RESOURCE_GROUP" \
    --revision REVISION_NAME
```

---

## Troubleshooting

- **Connection issues:** Ensure the PostgreSQL server allows connections from Azure Container Apps IP ranges
- **Build failures:** Check that all dependencies are correctly specified in `build.gradle`
- **Deployment errors:** Verify Azure CLI is logged in and has necessary permissions
- **Database connection:** Ensure the connection string includes SSL parameters for Azure PostgreSQL

---

## Cleanup

To remove all instances and the resource group (including the container app, PostgreSQL server, and any other resources in the group):

```bash
az group delete \
    --name $AZ_RESOURCE_GROUP \
    --yes
```

**Using the variable:** If your environment variables are set, `$AZ_RESOURCE_GROUP` resolves to `rgazure-deploy-inclusive` (or whatever value you gave `AZ_PROJECT_INC`).

**Hardcoded group name (no variables):**
```bash
az group delete \
    --name rgazure-deploy-inclusive \
    --yes
```

**Optional:** Add `--no-wait` to return immediately without waiting for the delete to finish:
```bash
az group delete \
    --name $AZ_RESOURCE_GROUP \
    --yes \
    --no-wait
```
