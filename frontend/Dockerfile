FROM node:20 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install -g @angular/cli && npm install

COPY . .

RUN ng build --configuration production

FROM nginx:stable

COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Exposer le port 80
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
