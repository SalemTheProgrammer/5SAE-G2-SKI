# Étape de construction Angular avec Node.js
FROM node:16 as build

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier le package.json et package-lock.json pour installer les dépendances
COPY ./frontend/gestion-station-ski-ui/package*.json ./

# Installer les dépendances Angular
RUN npm install

# Copier le reste des fichiers Angular
COPY ./frontend/gestion-station-ski-ui/ .

# Construire l'application Angular
RUN npm run build --prod

# Étape pour servir l'application avec Nginx
FROM nginx:alpine

# Copier les fichiers construits d'Angular dans le répertoire nginx
COPY --from=build /app/dist/gestion-station-ski-ui /usr/share/nginx/html

# Exposer le port 80 pour accéder à l'application Angular
EXPOSE 80

# Commande pour démarrer nginx
CMD ["nginx", "-g", "daemon off;"]
