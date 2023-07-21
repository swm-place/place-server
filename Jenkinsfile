pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo '🚀 Checking out...'
                git branch: env.GIT_BRANCH, credentialsId: 'Github-Jenkins-Yeoksi', url: 'https://github.com/swm-place/place-server'
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

                withCredentials([usernamePassword(credentialsId: 'ours_mariadb', usernameVariable: 'OURS_MARIADB_USERNAME', passwordVariable: 'OURS_MARIADB_PASSWORD')]) {

                    sh 'export MARIADB_HOST=localhost'
                    sh 'export MARIADB_PORT=3308'
                    sh 'export MARIADB_USERNAME=$OURS_MARIADB_USERNAME'
                    sh 'export MARIADB_PASSWORD=$OURS_MARIADB_PASSWORD'

                    sh './gradlew clean build'
                }

            }
            post {
                always {
                    echo '🚀 Cleaning up...'
                    sh 'unset MARIADB_HOST'
                    sh 'unset MARIADB_PORT'
                    sh 'unset MARIADB_USERNAME'
                    sh 'unset MARIADB_PASSWORD'
                }
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
                withCredentials([usernamePassword(credentialsId: 'ours_mariadb', usernameVariable: 'OURS_MARIADB_USERNAME', passwordVariable: 'OURS_MARIADB_PASSWORD'),
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