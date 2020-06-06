pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent{
                docker {
                    image 'maven:3-alpine`
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
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
                sh 'docker-compose up functional-tests'
            }
        }  
    }
    post{
        always{
            archiveArtifacts artifacts: 'docker-output/**'
            sh 'docker-compose down'
        }
    }
}
