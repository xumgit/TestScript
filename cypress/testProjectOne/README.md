# TestScript
## Thanks for you coming, best wishes for you.

## You can got to follow url to further study:  
https://www.cnblogs.com/xumBlog/p/10991467.html

## In package.json file, add follow command:  
### `npm run cy:open`            
    open cypess window
### `npm run cy:run`      
    run file
### `npm run cy:run:spec`      
    run spcial file

## How Start Cypress project
should be install Node.js , then open cmd:  
cd (testProjectOne path)/  
1. npm install => install environment requirement package, include cypress
2. node_modules\.bin\cypress open => if the 1 step success, this step will open cypress
3. then you select you want to run script
4. when the 1 step success, you can use command run special script with npx, follow command:  
   npx cypress run --spec "cypress/integration/test/CloneUpgradeSuccess.js"