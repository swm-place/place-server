pipeline {
    agent any

    environment {
        MARIADB_TEST_HOST = 'mariadb-test'
        MARIADB_TEST_PORT = '3307'
        MARIADB_TEST_ROOT_PASSWORD = 'root'
        MARIADB_TEST_DATABASE = 'ours'
        MARIADB_TEST_USER = 'tester'
        MARIADB_TEST_USER_PASSWORD = 'tester'

        DOCKER_IMAGE_NAME = 'been-api-main'
    }

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

        stage('Build and Test') {
            steps {
                echo '🚀 Preparing DB Dependencies for testing...'
                sh 'docker run \
                    -d \
                    --rm \
                    --name $MARIADB_TEST_HOST \
                    -p $MARIADB_TEST_PORT:3306 \
                    -e MYSQL_ROOT_PASSWORD=$MARIADB_TEST_ROOT_PASSWORD \
                    -e MYSQL_DATABASE=$MARIADB_TEST_DATABASE \
                    -e MYSQL_USER=$MARIADB_TEST_USER \
                    -e MYSQL_PASSWORD=$MARIADB_TEST_USER_PASSWORD \
                    mariadb:10.5.18'

                echo '🚀 Testing and Building...'
                sh 'docker buildx build \
                    --build-arg MARIADB_HOST=$MARIADB_TEST_HOST \
                    --build-arg MARIADB_PORT=$MARIADB_TEST_PORT \
                    --build-arg MARIADB_USERNAME=$MARIADB_TEST_USER \
                    --build-arg MARIADB_PASSWORD=$MARIADB_TEST_USER_PASSWORD \
                    --platform=linux/arm64 \
                    --target build \
                    -t $DOCKER_IMAGE_NAME \
                    .'
            }
            post {
                always {
                    echo '🚀 Cleaning up...'
                    sh 'docker stop mariadb-test'
                }
                success {
                    echo '☀️ Successfully built!'
                }
                failure {
                    echo '🌧️ Failed to build!'
                }
            }
        }

        stage('Build Docker') {
            steps {
                echo '🚀 Building...'
                sh 'docker buildx build --platform=linux/arm64 --target runner -t $DOCKER_IMAGE_NAME .'
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

        stage('Push to ECR') {
            when {
                anyOf {
                    branch 'develop'
                    branch 'main'
                }
            }
            steps {
                echo '🚀 Logging in to ECR...'
                sh 'aws ecr get-login-password --region $AWS_DEFAULT_REGION | \
                    docker login --username AWS --password-stdin \
                    $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com'

                echo '🚀 Pushing to ECR...'
                sh 'docker tag been-api-gateway-scg:latest \
                    $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$DOCKER_IMAGE_NAME:latest'
                sh 'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$DOCKER_IMAGE_NAME:latest'
            }
            post {
                always {
                    echo '🚀 Logging out from ECR...'
                    sh 'docker logout'
                }
                success {
                    echo '☀️ Successfully pushed to ECR!'
                }
                failure {
                    echo '🌧️ Failed to push to ECR!'
                }
            }
        }

        stage('Deploy #dev') {
            when {
                branch 'develop'
            }
            steps {
                echo '🚀 Deploying to dev server...'
                sh 'aws ecs update-service --cluster $AWS_BEEN_ECS_CLUSTER_NAME --service $AWS_BEEN_ECS_GATEWAY_SERVICE_NAME --force-new-deployment'
            }
            post {
                success {
                    echo '☀️ Successfully deployed to development server!'
                }
                failure {
                    echo '🌧️ Failed to deploy to development server!'
                }
            }
        }

        stage('Deploy #prod') {
            when {
                branch 'main'
            }
            steps {
                echo 'not implemented.'
            }
            post {
                success {
                    echo '☀️ Successfully deployed to production server!'
                }
                failure {
                    echo '🌧️ Failed to deploy to production server!'
                }
            }
        }

        stage('Clean') {
            steps {
                echo '🚀 Cleaning up...'
                sh 'rm -rf ./*'
            }
            post {
                success {
                    echo '☀️ Successfully cleaned up!'
                }
                failure {
                    echo '🌧️ Failed to clean up!'
                }
            }
        }
    }
}