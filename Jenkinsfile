pipeline {
    agent any
    
    environment {
        SONARQUBE_LOGIN = credentials('sonarqube-login') 
        NEXUS_URL = 'http://192.168.33.10:8081/repository/maven-releases' 
    }

    stages {
        stage('GIT Checkout') {
            steps {
                git branch: 'DhiaeddineUwobikundiye-5SAE7-G2', url: 'https://github.com/SalemTheProgrammer/5SAE-G2-SKI.git'
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

        stage('Sonarqube') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Dhianoob456.'
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
        
       

    }

  
}