function searchLinks() {
    const query = document.getElementById('search-query').value;
    $.ajax({
        url: '/dashboards/search',
        type: 'GET',
        data: { query: query },
        success: function (data) {
            $('#accordion-container').html(data); // Update the accordion content
            initializeAccordion(); // Reapply accordion logic to the updated content
        },
        error: function (err) {
            console.error('Error fetching search results:', err);
        }
    },300);
}

