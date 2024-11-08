# Étape 1 : Utiliser une image Node.js pour construire l'application Angular
FROM node:20 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier package.json et package-lock.json pour installer les dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le reste des fichiers du projet Angular
COPY . .

# Construire l'application Angular en mode production
RUN npm run build -- --project=frontend --configuration production

# Étape 2 : Utiliser une image Apache pour servir l'application
FROM httpd:alpine

# Copier les fichiers construits dans le répertoire de Nginx
COPY --from=build /app/dist/frontend /usr/local/apache2/htdocs/

# Exposer le port 80 pour l'accès depuis l'extérieur
EXPOSE 80

# Lancer Apache
CMD ["httpd-foreground"]
