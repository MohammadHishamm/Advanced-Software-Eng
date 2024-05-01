/* Set rates + misc */
var taxRate = 0.05;
var shippingRate = 15.00; 
var fadeTime = 300;

/* Assign actions */
document.querySelectorAll('.product-quantity input').forEach(function(input) {
  input.addEventListener('change', function() {
    updateQuantity(this);
  });
});

document.querySelectorAll('.product-removal button').forEach(function(button) {
  button.addEventListener('click', function() {
    removeItem(this);
  });
});

/* Recalculate cart */
function recalculateCart() {
  var subtotal = 0;
  
  /* Sum up row totals */
  document.querySelectorAll('.product').forEach(function(product) {
    subtotal += parseFloat(product.querySelector('.product-line-price').textContent);
  });
  
  /* Calculate totals */
  var tax = subtotal * taxRate;
  var shipping = (subtotal > 0 ? shippingRate : 0);
  var total = subtotal + tax + shipping;
  
  /* Update totals display */
  document.querySelectorAll('.totals-value').forEach(function(element) {
    element.style.display = 'none';
  });
  
  setTimeout(function() {
    document.getElementById('cart-subtotal').textContent = subtotal.toFixed(2);
    document.getElementById('cart-tax').textContent = tax.toFixed(2);
    document.getElementById('cart-shipping').textContent = shipping.toFixed(2);
    document.getElementById('cart-total').textContent = total.toFixed(2);
    
    if(total == 0) {
      document.querySelector('.checkout').style.display = 'none';
    } else {
      document.querySelector('.checkout').style.display = 'block';
    }
    
    document.querySelectorAll('.totals-value').forEach(function(element) {
      element.style.display = 'block';
    });
  }, fadeTime);
}

/* Update quantity */
function updateQuantity(quantityInput) {
  /* Calculate line price */
  var productRow = quantityInput.parentElement.parentElement;
  var price = parseFloat(productRow.querySelector('.product-price').textContent);
  var quantity = quantityInput.value;
  var linePrice = price * quantity;
  
  /* Update line price display and recalc cart totals */
  productRow.querySelectorAll('.product-line-price').forEach(function(element) {
    element.style.display = 'none';
    setTimeout(function() {
      element.textContent = linePrice.toFixed(2);
      recalculateCart();
      element.style.display = 'block';
    }, fadeTime);
  });  
}

/* Remove item from cart */
function removeItem(removeButton) {
  /* Remove row from DOM and recalc cart total */
  var productRow = removeButton.parentElement.parentElement;
  productRow.style.display = 'none';
  setTimeout(function() {
    productRow.remove();
    recalculateCart();
  }, fadeTime);
}
