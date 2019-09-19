GIT Commands for project workflow

Make sure you are in your project repo folder:

1. Check your current branch:
  *git branch
  
2. Create new branch: 
  *git checkout -b priyaBranch
  
3. Set branch for upstream(Github will track branch):
  *git push --set-upstream origin priyaBranch
  
4. Make your changes then create merge request and have review/merged:

5. Git add:
  *git add -A
  
6. GIT commit:
  *git commit -m "Message"
  
7. Commit branch:
  *git push origin priyaBranch
  
8. Go to Github and create new pull request.
https://github.com/aleal629/Bookstore

**Make sure branch is merged

9. Change back to master
  *git checkout master

10. Delete branch:
  *git branch -d priyaBranch
