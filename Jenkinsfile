pipeline {
    agent any

    environment {
        // Define your SonarQube and Nexus credentials securely in Jenkins
        SONARQUBE_LOGIN = credentials('sonarqube-login') // Use Jenkins credentials
        NEXUS_URL = 'http://your-nexus-url/repository/maven-releases' // Update with your Nexus URL
    }

    stages {
        stage('GIT Checkout') {
            steps {
                echo "Cloning the repository from GitHub..."
                git branch: 'SALEMDAHMANI-5SAE07-G2', url: 'https://github.com/SalemTheProgrammer/5SAE-G2-SKI.git'
            }
        }

        stage('MVN CLEAN') {
            steps {
                echo "Running Maven Clean"
                sh 'mvn clean'
            }
        }

        stage('MVN COMPILE') {
            steps {
                echo "Running Maven Compile"
                sh 'mvn compile'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo "Running SonarQube Analysis"
                sh 'mvn sonar:sonar -Dsonar.login=${SONARQUBE_LOGIN}'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                echo "Deploying to Nexus..."
                sh 'mvn deploy -Dmaven.test.skip=true -DaltDeploymentRepository=nexus::default::${NEXUS_URL}'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
