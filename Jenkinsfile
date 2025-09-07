pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rohanrode02/demo-bank-repo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                sh 'mvn sonar:sonar -Dsonar.projectKey=banking-system -Dsonar.login=${SONAR_AUTH_TOKEN}'
            }

            }
        }
        

        stage('Deploy to Tomcat') {
            steps {
                // WAR deploy करण्यासाठी Jenkins plugin वापरू शकतो
                deploy adapters: [tomcat9(credentialsId: 'tomcat-admin', path: '', url: 'http://localhost:8091')], 
                       contextPath: '/banking-system', 
                       war: '**/target/banking-system.war'
            }
        }
    }
}
