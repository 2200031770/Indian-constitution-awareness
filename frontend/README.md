# Samvidhan frontend

The Angular application provides the learning library, Constitution topic
guides, and interactive knowledge check. It reads topic and quiz content from
the Spring Boot API, which uses MySQL as its only runtime database.

## Development

Start MySQL and the backend first. From the project root, see
[the setup guide](../README.md) for creating the database and starting Spring
Boot. Then run:

```powershell
npm install
npm start
```

Open `http://localhost:4200`. The Angular development server uses
[`proxy.conf.json`](./proxy.conf.json) to forward `/api` requests to
`http://localhost:8080`.

## Checks

```powershell
npm test
npm run build
```

Production deployments should route `/api` to the backend through the hosting
platform's reverse proxy or gateway.
