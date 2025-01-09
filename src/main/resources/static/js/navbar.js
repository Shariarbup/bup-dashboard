function toggleNavbar() {
    const navbar = document.getElementById('verticalNavbar');
    if (navbar.classList.contains('show')) {
        navbar.classList.remove('show');
    } else {
        navbar.classList.add('show');
    }
}