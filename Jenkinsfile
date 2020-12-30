pipeline {
    
   agent any
    tools {
        gradle "GRADLE"
    }
    
   stages {
       
       stage('Git clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/kpzaolod6000/Maxima-on-Android-AS.git'

            }
        }
        stage('Build and Test') {
	        steps {
                script{
	                try {
                        withGradle {
                            bat 'gradle clean build'
                        }
                        currentBuild.result = 'SUCCESS'
                    }catch(error) {
                        slackSend channel: '#build-failures', color: 'bad', message: "This build is broken ${env.BUILD_URL}", token: 'XXXXXXXXXXX'
                        currentBuild.result = 'FAILURE'
                    }finally {
                        publishHTML (target : [allowMissing: false,
                         alwaysLinkToLastBuild: true,
                         keepAll: true,
                         reportDir: 'app/build/reports',
                         reportFiles: ' lint-results.html',
                         reportName: 'Overview',
                         reportTitles: 'Overview'])
                    }
	            }
                
	        }
        }
        
      stage('SonarQube Analisis') {
        steps {
            echo 'SonarCube...'
                withSonarQubeEnv('SonarQube') { 
                    bat "sonar-scanner.bat"
                }
            }
        }
        stage('Test Funcional') {
	        steps{
	            script{
	                try {   
                        lock('emulator') {
                            bat 'gradle connectedCheck'
                        }   
                        currentBuild.result = 'SUCCESS'
                    }catch(error) {
                        echo 'Error en la fase de Test Funcional'
                    }finally {
                        publishHTML (target : [allowMissing: false,
                         alwaysLinkToLastBuild: true,
                         keepAll: true,
                         reportDir: 'app/build/reports/androidTests/connected',
                         reportFiles: '*.html',
                         reportName: 'Test Registers',
                         reportTitles: 'The Report'])
                        }
	                }
                }
            }
        }
        stage('Deploy') {
            //archiveArtifacts 'app/build/outputs/apk/*'
            echo 'deploy .. '
        } 
    }
}