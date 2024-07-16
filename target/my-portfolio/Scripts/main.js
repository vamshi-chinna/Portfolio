const nav = document.querySelector("nav"); // Select the first nav element
const sticky = nav.offsetTop; // Get the offset top of the nav element

window.addEventListener("scroll", () => { // Correct the event listener
    if (window.scrollY > sticky) {
        nav.classList.add("sticky"); // Add sticky class when scrollY is greater than or equal to sticky
    } else {
        nav.classList.remove("sticky"); // Remove sticky class when scrollY is less than sticky
    }
});
