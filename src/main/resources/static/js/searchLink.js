function searchLinks() {
    const query = document.getElementById('search-query').value;
    $.ajax({
        url: '/dashboards/search', // Backend search endpoint
        type: 'GET',
        data: { query: query },
        success: function (data) {
            $('#accordion-container').html(data); // Update the accordion content dynamically
        },
        error: function (err) {
            console.error('Error fetching search results:', err);
        }
    });
}