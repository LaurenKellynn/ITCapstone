
/*
 * Programmer Name: Lauren Kellynn
 * Date: 2/12/2023
 * IT Capstone Project: JavaScript for shopping page and cart
 */

const cartIcon = document.querySelector(".cart-icon");
const cart = document.querySelector(".cart");
const cartClose = document.querySelector(".close-cart");

cartIcon.addEventListener('click', () =>  {
cart.classList.add("active");
});

cartClose.addEventListener('click', () =>  {
cart.classList.remove("active");
});

if (document.readyState === 'loading'){
document.addEventListener('DOMContentLoaded', ready);
}else{
ready();
}

function checkUrlForCartUpdate() {
	const urlParams = new URLSearchParams(window.location.search);
	if (urlParams.get('cart_updated') === '1') {
	  cart.classList.add('active');
	}
  }

//remove items from the shopping cart
function ready(){
var removeCartButtons = document.getElementsByClassName("cart-remove");
console.log(removeCartButtons);
for (var i = 0; i < removeCartButtons.length; i++){
var button = removeCartButtons[i];
button.addEventListener('click', removeCartItem);
checkUrlForCartUpdate();
}

//Changes in Quantity
var quantityInputs = document.getElementsByClassName("cart-quantity");
for (var i = 0; i < quantityInputs.length; i++){
var input = quantityInputs[i];
input.addEventListener('change', quantityChanged);
}

//add items to cart
var addCart = document.getElementsByClassName("add-cart");
for (var i = 0; i < addCart.length; i++){
var button = addCart[i];
button.addEventListener('click', addCartClicked);
}

//Checkout Button
document.getElementsByClassName("btn-buy")[0].addEventListener('click', checkoutClicked);
document.getElementsByClassName("ok")[0].addEventListener('click', closePopup);
}

//Checkout function
function checkoutClicked(){
header("Location: cart.php?checkout=true");
var cartContents = document.getElementsByClassName("cart-content")[0];
while (cartContents.hasChildNodes()){
cartContents.removeChild(cartContents.firstChild);
}
updateTotal();
removeBadge();
const urlParams = new URLSearchParams(window.location.search);
const checkout = urlParams.get('checkout');
if (checkout === 'true') {
  openPopup(); // If the 'checkout' parameter is set, show the confirmation popup
}
}

function openPopup(){
popup.classList.add("open-popup");
}

function closePopup(){
popup.classList.remove("open-popup");
}

function removeCartItem(event){
var buttonClicked = event.target;
buttonClicked.parentElement.remove();
updateTotal();
}

function quantityChanged(event){
var input = event.target;
if (isNaN(input.value) || input.value <= 0) {input.value = 1;}
updateTotal();
}

//add items to the cart
function addCartClicked(event){
var button = event.target;
var products = button.parentElement;
var title = products.getElementsByClassName("title")[0].innerText;
var price = products.getElementsByClassName("price")[0].innerText;
var productImg = products.getElementsByClassName("product-img")[0].src;
addToCart(title, price, productImg);
updateTotal();
addBadge();
}

function addBadge(){
badge.classList.add("open-badge");
}

function removeBadge(){
badge.classList.remove("open-badge");
}

function addToCart(title, price, productImg){
	console.log("addToCart() function called.");
	var cartShopBox = document.createElement("div");
    cartShopBox.classList.add("cart-box");
    var cartItems = document.getElementsByClassName("cart-content")[0];
    var cartItemsNames = document.getElementsByClassName("cart-product-title");
    for (var i = 0; i < cartItemsNames.length; i++){
        if (cartItemsNames[i].innerText == title){
            alert("You already have this item in your shopping cart! Please update the quantity in the shopping cart.");
            return;
        }
    }
var cartBoxContents = `<img src="${productImg}" alt="" class="cart-img">
    <div class="detail-box">
        <div class="cart-product-title">${title}</div>
        <div class="cart-price">${formattedPrice}</div>
        <input type="number" value="1" class="cart-quantity">
    </div>
    <ion-icon name="trash-outline" class="cart-remove"></ion-icon>`;
    cartShopBox.innerHTML = cartBoxContents;
    cartItems.append(cartShopBox);

    // Extract the data attributes from the cart box element
    var cartBox = cartItems.lastChild;
    var title = cartBox.getAttribute('data-title');
    var price = cartBox.getAttribute('data-price');
    var image = cartBox.getAttribute('data-image');
    var quantity = cartBox.getElementsByClassName('cart-quantity')[0];

    // Add event listeners for the remove and quantity change functions
    cartBox.getElementsByClassName("cart-remove")[0].addEventListener('click', removeCartItem);
    quantity.addEventListener('change', quantityChanged);
}

//update the total in the shopping cart
function updateTotal(){
var cartContents = document.getElementsByClassName("cart-content")[0];
var cartBoxes = cartContents.getElementsByClassName("cart-box");
var total = 0;
for (var i = 0; i < cartBoxes.length; i++){
var cartBox = cartBoxes[i];
var priceElement = cartBox.getElementsByClassName("cart-price")[0];
var quantityElement = cartBox.getElementsByClassName("cart-quantity")[0];
var price = parseFloat(priceElement.innerText.replace("$", ""));
var quantity = quantityElement.value;
total = total + price * quantity;
}
//document.getElementsByClassName("total-price")[0].innerText = "$" + total;
document.getElementsByClassName("total-price")[0].innerText = "$" + total.toFixed(2);
//document.getElementById("total-price").innerHTML = total;
document.getElementById("total-price").innerHTML = "$" + total.toFixed(2);
}