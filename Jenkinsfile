pipeline{
	agent{ docker { image 'maven:3.3.3'}}
	
	stage {
		stage('build') {
			
				steps { 
					sh 'mvn --versions'
					sh 'mvn -B -DskipTests clean package
					}
			}
			
	}




}