# Stage 1: Build the Angular application
FROM node:20 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json (if available) to install dependencies first
# This takes advantage of Docker's layer caching
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the Angular project files into the container
COPY . .

# Build the Angular project for production (replace <project-name> with your actual project name)
RUN npm run build -- --project=frontned-gestion-de-ski --configuration production

# Stage 2: Serve the Angular application with Nginx
FROM nginx:alpine

# Remove default Nginx static files
RUN rm -rf /usr/share/nginx/html/*

# Copy the built Angular files from the previous stage to Nginx's HTML directory
COPY --from=build /app/dist/frontned-gestion-de-ski /usr/share/nginx/html

# Expose port 80 to the host
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
