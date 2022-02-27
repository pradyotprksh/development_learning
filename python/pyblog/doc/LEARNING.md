# LEARNING

## Project Structure
Before I start working on any project, the first thing I do is to look what is the easiest and comfortable way to 
structure your project.

It's a crucial topic in any project. How to structure the project. Because in the long run if the structure is not good 
then it will create a lot of issues related to understanding and testing.

After some searching and banging my head on the laptop I found https://docs.python-guide.org/writing/structure/.

It gives the details on how you should be structuring the project, but it will vary on the type of project you are 
working on.

## Ignore Files

Since, I am going to push the code to GitHub it's better to ignore those files which are not needed and need to be 
protected for security reasons.

I am not sure which files to ignore in Python, so I found a repository which provides the .gitignore for different 
languages. You can find the details here https://github.com/github/gitignore.

## Firebase

I thought of making it a device specific project, but where is the fun in that. So thought the easiest one to work with 
in backend perspective is Firebase. Since I have prior knowledge on how it works, so it was easier to pick it.

But how can I integrate Firebase with Python? 🤔

Initially I thought it would be using rest api calls, but what about realtime update? Can we achieve it? What about 
authentication? And also storage? I was this close to drop the idea then I found 
https://github.com/firebase/firebase-admin-python. A Firebase library for Python. This is used basically for admin but 
for now I will use it as a standalone project (might be).

So the features which I will be using from Firebase would be mainly Authentication, Firestore and Storage. Might 
include more but that will depend on the project future and my mood 😅.

## Regex

A common headache while creating a project is validation of data. And to do that usually we need regular expressions.
Like for email, password strength, etc.

And after few searching found https://regex101.com to check my expressions and also find examples as well. I got what I 
needed from this site and everything works as expected by me 😅.

