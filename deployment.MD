# Steps to deploy the API with Docker.

1.-Open Docker.

2.-Open "TestJJ" (This is the folder that contains my test).

3.-Open a terminal and find the folder mentioned in step 2. 
	Command: cd Path
	Example: cd D:\STS4\TestJJ\TestJJ

4.-Time to build the image with docker.
	Command: docker build -t "testJJ-docker" .
	Note: You can name your image with other name.  

5(Optional).- Check if the image is already built.
	Command: docker images

6.-Create a container from the given image and starts the container.
	Command: docker run --name TestJJDocker -p:9000:8080 testJJ-docker:latest  
	Note: if you have changed the name of the image before (step 4), you must update the name also in this command line.  
