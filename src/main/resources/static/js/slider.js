let slideIndex = 1; // Start from the first real slide
const slides = document.querySelectorAll(".slide");
const dots = document.querySelectorAll(".dot");
const slidesContainer = document.querySelector(".slides");
const totalSlides = slides.length;

// Clone the first and last slides for a seamless loop
const firstClone = slides[0].cloneNode(true);
const lastClone = slides[slides.length - 1].cloneNode(true);

slidesContainer.appendChild(firstClone); // Add first slide to the end
slidesContainer.prepend(lastClone);      // Add last slide to the beginning

// Set initial position
slidesContainer.style.transform = `translateX(-100%)`;

// Change slide (forward or backward)
function changeSlide(n) {
    slideIndex += n;
    updateSlidePosition();
}

// Go to the current slide based on dot click
function currentSlide(n) {
    slideIndex = n;
    updateSlidePosition();
}

// Update slide position with seamless looping
function updateSlidePosition() {
    slidesContainer.style.transition = "transform 0.5s ease-in-out"; // Enable smooth transition
    slidesContainer.style.transform = `translateX(-${slideIndex * 100}%)`;

    // Handle the transition end for seamless looping
    slidesContainer.addEventListener("transitionend", () => {
        if (slideIndex === 0) {
            // Snap to the last real slide
            slidesContainer.style.transition = "none"; // Disable transition
            slideIndex = totalSlides; // Set to the last real slide
            slidesContainer.style.transform = `translateX(-${slideIndex * 100}%)`;
        }
        if (slideIndex === totalSlides + 1) {
            // Snap to the first real slide
            slidesContainer.style.transition = "none"; // Disable transition
            slideIndex = 1; // Set to the first real slide
            slidesContainer.style.transform = `translateX(-${slideIndex * 100}%)`;
        }
    });

    // Update dots
    dots.forEach(dot => dot.classList.remove("active"));
    const currentDotIndex = (slideIndex - 1 + totalSlides) % totalSlides; // Ensure valid dot index
    dots[currentDotIndex].classList.add("active");
}

// Auto-slide functionality
setInterval(() => {
    changeSlide(1); // Automatically move to the next slide
}, 5000);

window.addEventListener("load", function() {
    // Initialize the slider after everything is loaded
    initializeSlider();
});

// Slider initialization function
function initializeSlider() {
    slideIndex = 1;
    updateSlidePosition();
}

// Listen for visibility change or tab focus event
document.addEventListener("visibilitychange", function () {
    if (!document.hidden) {
        // Re-initialize or refresh the slider when the tab is active
        refreshSlider();
    }
});

// Or use the focus event (works for some browsers)
window.addEventListener('focus', function () {
    // Re-initialize or refresh the slider when the tab gets focus
    refreshSlider();
});

// Slider refresh function
function refreshSlider() {
    const slider = document.querySelector('.image-slider-container');
    // Ensure slider is properly initialized
    if (slider) {
        initializeSlider();
    }
}
