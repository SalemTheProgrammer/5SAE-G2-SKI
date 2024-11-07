# Étape 1 : Utiliser une image Node.js pour construire l'application Angular
FROM node:20 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers de configuration npm pour installer les dépendances
COPY package*.json ./

# Installer les dépendances, y compris Angular CLI
RUN npm install -g @angular/cli && npm install

# Copier le reste du code source
COPY . .

# Construire l'application Angular en mode production
RUN ng build --configuration production

# Étape 2 : Utiliser une image Nginx pour servir l'application
FROM nginx:alpine

# Copier les fichiers construits dans le répertoire de Nginx
COPY --from=build /app/dist/ /usr/share/nginx/html

# Exposer le port 80
EXPOSE 80

# Démarrer Nginx en mode premier plan
CMD ["nginx", "-g", "daemon off;"]
