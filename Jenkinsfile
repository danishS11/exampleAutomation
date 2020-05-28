pipeline {
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                sh "docker build -t=danishaj/qa-docker ."
            }
        }
 /*       stage('Push Image') {
            steps {
               withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "docker login --username=${user} --password=${pass}"
                    sh "docker push danishaj/qa-docker:latest"
                }
            }                           
        } */
        stage('Initialize Hub and Chrome') {
            steps{
                sh 'docker-compose up -d hub chrome'
            }
        }
        stage('Run Tests') {
            steps{
                sh 'docker-compose up functional-tests --no-color'
            }
        }  
    }
    post{
        always{
            archiveArtifacts artifacts: 'test-output/**'
            sh 'docker-compose down'
        }
    }
}
