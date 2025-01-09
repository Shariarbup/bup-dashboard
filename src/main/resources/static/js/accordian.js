document.addEventListener('DOMContentLoaded', function () {
    initializeAccordion();
});

function initializeAccordion() {
    const accordionItemHeaders = document.querySelectorAll('.accordion-item-header');

    accordionItemHeaders.forEach(accordionItemHeader => {
        accordionItemHeader.classList.add("active");
        const accordionItemBody = accordionItemHeader.nextElementSibling;
        accordionItemBody.style.maxHeight = accordionItemBody.scrollHeight + "px";

        accordionItemHeader.addEventListener("click", event => {
            accordionItemHeader.classList.toggle("active");
            const accordionItemBody = accordionItemHeader.nextElementSibling;
            if (accordionItemHeader.classList.contains("active")) {
                accordionItemBody.style.maxHeight = accordionItemBody.scrollHeight + "px";
            } else {
                accordionItemBody.style.maxHeight = 0;
            }
        });
    });
}