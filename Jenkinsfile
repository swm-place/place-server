pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo '🚀 Checking out...'
                git branch: env.GIT_BRANCH, credentialsId: 'github_yeoksi_coldot', url: 'git@github.com:swm-place/place-server.git'
            }
            post {
                success {
                    echo '☀️ Successfully checked out!'
                }
                failure {
                    echo '🌧️ Failed to check out!'
                }
            }
        }

        stage('Build') {
            steps {
                echo '🚀 Building...'
                sh './gradlew clean build'
            }
            post {
                success {
                    echo '☀️ Successfully built!'
                }
                failure {
                    echo '🌧️ Failed to build!'
                }
            }
        }

        stage('Deploy - Dev') {
            when {
                branch 'develop'
            }
            steps {
                withCredentials([string(credentialsId: 'ours_mariadb_username', variable: 'OURS_MARIADB_USERNAME'),
                                 string(credentialsId: 'ours_mariadb_password', variable: 'OURS_MARIADB_PASSWORD'),
                                 string(credentialsId: 'ours_mariadb_root_password', variable: 'OURS_MARIADB_ROOT_PASSWORD')]) {

                    echo '🚀 Deploying to dev server...'

                    sh 'export OURS_MARIADB_USERNAME=$OURS_MARIADB_USERNAME'
                    sh 'export OURS_MARIADB_PASSWORD=$OURS_MARIADB_PASSWORD'
                    sh 'export OURS_MARIADB_ROOT_PASSWORD=$OURS_MARIADB_ROOT_PASSWORD'
                    sh 'docker-compose -f docker-compose.dev.yaml up -d --build'
                }
            }
            post {
                always {
                    echo '🚀 Cleaning up...'
                    sh 'unset OURS_MARIADB_USERNAME'
                    sh 'unset OURS_MARIADB_PASSWORD'
                    sh 'unset OURS_MARIADB_ROOT_PASSWORD'
                }
                success {
                    echo '☀️ Successfully deployed to dev server!'
                }
                failure {
                    echo '🌧️ Failed to deploy to dev server!'
                }
            }
        }
    }
}