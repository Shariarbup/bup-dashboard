document.addEventListener('DOMContentLoaded', function () {
    const block = document.getElementById('first-block');
    const cards = document.querySelectorAll('.card');

    // Function to animate the count-up effect
    function animateCountUp(elementId, startValue, endValue, duration) {
        let current = startValue;
        const increment = endValue / (duration / 50); // Increment value for smooth animation

        const interval = setInterval(function() {
            current += increment;
            if (current >= endValue) {
                current = endValue;
                clearInterval(interval); // Stop once the end value is reached
            }
            document.getElementById(elementId).textContent = Math.floor(current);
        }, 50); // Update every 50ms
    }

    // Simulate loading of statistics with a delay
    setTimeout(function () {
        animateCountUp('students-count', 0, 9835, 2000);
        animateCountUp('teachers-count', 0, 515, 2000);
        animateCountUp('officers-count', 0, 310, 2000);
        animateCountUp('courses-count', 0, 100, 2000);
    }, 1000);

    // Intersection Observer to trigger animation when the block comes into view
    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // Trigger animation when block is in view
                block.style.opacity = 1; // Fade the block in

                // Apply animation classes to cards
                cards.forEach((card, index) => {
                    setTimeout(() => {
                        card.style.opacity = 1; // Make the card visible with fade-in effect
                    }, index * 300); // Stagger the animation
                });

                // Stop observing once animation is triggered
                observer.disconnect();
            }
        });
    }, { threshold: 0.5 }); // Observe when 50% of the block is visible

    // Start observing the block
    observer.observe(block);
});
