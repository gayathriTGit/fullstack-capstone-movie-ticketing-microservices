pipeline {
    agent any
    
    stages {
     
        stage('Checkout') {
            steps { 
                script {
                    git branch: 'main', url: 'https://github.com/gayathriTGit/fullstack-capstone-movie-ticketing-microservices.git'
                }
            }
        }
      
        stage('Build') {
	    agent { label 'docker' }   // runs on the docker host
            steps {
                sh '''
                docker version
                docker-compose -f docker-compose.yml down || true 
                docker-compose -f docker-compose.yml up -d
                '''
            }
        }
    
    }
}
