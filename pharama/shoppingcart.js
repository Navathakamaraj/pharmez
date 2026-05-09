document.querySelectorAll(".fle3").forEach((row) => {
  const minus = row.querySelector(".minus");
  const plus = row.querySelector(".plus");
  const qty = row.querySelector(".qty-number");
  const total = row.querySelector(".total-price");
  const priceElement = row.querySelector(".price");
  const removeBtn = row.querySelector(".remove-btn");

  const pricePerItem = parseFloat(priceElement.textContent.replace("$", ""));
  let count = parseInt(qty.textContent);

  function updateTotal() {
    let totalAmount = count * pricePerItem;
    total.textContent = `${totalAmount.toFixed(2)}`;
    updateSummary();
  }

  plus.addEventListener("click", function () {
    count++;
    qty.textContent = count;
    updateTotal();
  });

  minus.addEventListener("click", function () {
    if (count > 1) {
      count--;
      qty.textContent = count;
      updateTotal();
    }
  });

  removeBtn.addEventListener("click", function () {
    row.remove();
    updateSummary();
  });

  updateTotal();
});

// Update subtotal & grand total dynamically
function updateSummary() {
  let subtotal = 0;
  document.querySelectorAll(".total-price").forEach((tp) => {
    subtotal += parseFloat(tp.textContent.replace("$", ""));
  });
  document.getElementById("subtotal").textContent = `$${subtotal.toFixed(2)}`;
  let grand = subtotal + 8; // shipping fixed
  document.getElementById("grandtotal").textContent = `$${grand.toFixed(2)}`;
}
