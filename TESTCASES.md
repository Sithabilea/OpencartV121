# Test Scenarios

## Account Registration Test
Objective: Verify that a user can register an account by providing valid user information.

Steps:
1. Navigate to Home page
2. Click 'My Account'
3. Click 'Register'
4. Enter New Account Details into the mandatory fields
5. Click 'Continue'

Expected Result:
Account is created and user redirected to account dashboard

---

## Login Test
Objective: Verify that a registered user can login.

Steps:
1. Navigate to Login page
2. Enter valid credentials
3. Click 'login'

Expected Result:
User is redirected to the account dashboard.

---

## Login Test DDT
Objective 1: Verify that a registered user can login.
Objective 2: Verify that an unregistered user cannot login.

Steps:
1. Navigate to login page
2. Enter valid credentials
3. Click login

Expected Results:
1. A register user is redirected to the account dashboard.
2. An account is not created for an unregistered user with a warning message displayed instead.

---

## Search Product
Objective: Verify searching an existing product.

Steps:
1. Navigate to Products Search page
2. Enter any existing product name into 'Search' text fields
3. Click on search icon

Expected Result:
Product is displayed in the search results.

---

## Add Product to Cart
Objective: Verify product can be added to cart.

Steps:
1. Search product
2. Click Add to Cart

Expected Result:
Product appears in shopping cart.