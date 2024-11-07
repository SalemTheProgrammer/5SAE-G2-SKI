# Use a Node image to run the Angular development server
FROM node:20 AS dev

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the Angular project files into the container
COPY . .

# Expose port 4200 to access the Angular app in development mode
EXPOSE 4200

# Run the Angular application in development mode
CMD ["npm", "run", "start", "--", "--host=192.168.33.10"]
