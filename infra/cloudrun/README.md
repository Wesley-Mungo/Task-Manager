# Deploy to Google Cloud Run

## Prerequisites
- Google Cloud project
- `gcloud` CLI installed and authenticated
- Artifact Registry repository created (optional if pushing to GHCR)

## Build & Push Images (GHCR example)
Images are pushed by GitHub Actions to:
- `ghcr.io/OWNER/REPO/backend:latest`
- `ghcr.io/OWNER/REPO/frontend:latest`

Alternatively, build locally and push to Artifact Registry:
```bash
# Backend
gcloud builds submit --tag REGION-docker.pkg.dev/PROJECT/REPO/backend:latest backend

# Frontend
gcloud builds submit --tag REGION-docker.pkg.dev/PROJECT/REPO/frontend:latest frontend
```

## Deploy Backend to Cloud Run
```bash
gcloud run deploy taskmgr-backend \
  --image=REGISTRY/backend:latest \
  --platform=managed \
  --allow-unauthenticated \
  --region=REGION \
  --port=8080 \
  --set-env-vars=SPRING_DATASOURCE_URL="jdbc:mysql://HOST:3306/DB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" \
  --set-env-vars=SPRING_DATASOURCE_USERNAME="USERNAME" \
  --set-env-vars=SPRING_DATASOURCE_PASSWORD="PASSWORD" \
  --set-env-vars=SPRING_JPA_HIBERNATE_DDL_AUTO=update \
  --set-env-vars=SPRING_SECURITY_JWT_SECRET="changemechangemechangemechangeme" \
  --memory=512Mi
```

## Deploy Frontend to Cloud Run
Point the frontend to the backend URL by setting an environment variable (optional), or build-time env.

```bash
gcloud run deploy taskmgr-frontend \
  --image=REGISTRY/frontend:latest \
  --platform=managed \
  --allow-unauthenticated \
  --region=REGION \
  --port=80 \
  --memory=256Mi
```

After deployment, update CORS in backend if needed to allow the frontend URL.
