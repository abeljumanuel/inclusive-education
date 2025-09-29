// Example: Mobile menu toggle
const menuBtn = document.querySelector("#menu-btn");
const menu = document.querySelector("#menu");

if (menuBtn && menu) {
    menuBtn.addEventListener("click", () => {
        menu.classList.toggle("hidden");
    });
}

// Example: Scroll animation (lightweight, no GSAP yet)
document.addEventListener("scroll", () => {
    document.querySelectorAll("section").forEach((section) => {
        const rect = section.getBoundingClientRect();
        if (rect.top < window.innerHeight * 0.85) {
            section.classList.add("opacity-100", "translate-y-0");
        }
    });
});

// Carousel logic
const carousel = document.getElementById("carousel");
const slides = carousel.children;
let index = 0;

function showSlide(i) {
    index = (i + slides.length) % slides.length;
    carousel.style.transform = `translateX(-${index * 100}%)`;
}

document.getElementById("prev").addEventListener("click", () => showSlide(index - 1));
document.getElementById("next").addEventListener("click", () => showSlide(index + 1));

// Optional: autoplay every 6s
setInterval(() => showSlide(index + 1), 6000);

// Modals
const modalTriggers = document.querySelectorAll("[data-modal]");
const closeButtons = document.querySelectorAll("[data-close]");

modalTriggers.forEach(btn => {
    btn.addEventListener("click", () => {
        const modal = document.getElementById(btn.dataset.modal);
        modal.classList.remove("hidden");
        modal.classList.add("flex");
    });
});

closeButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        btn.closest("div[id^='modal']").classList.add("hidden");
    });
});

// Close modal when clicking outside
document.querySelectorAll("div[id^='modal']").forEach(modal => {
    modal.addEventListener("click", (e) => {
        if (e.target === modal) modal.classList.add("hidden");
    });
});

// =============================
// Modal Galería Visual
// =============================
const galleryModal = document.getElementById("galleryModal");
const openGallery = document.querySelector("a[href='#gallery']"); // Botón/enlace que abre la galería
const closeGallery = document.getElementById("closeGallery");

if (openGallery) {
  openGallery.addEventListener("click", (e) => {
    e.preventDefault();
    galleryModal.classList.remove("hidden");
  });
}

closeGallery.addEventListener("click", () => {
  galleryModal.classList.add("hidden");
});

// =============================
// Carousel Galería
// =============================
const galleryCarousel = document.getElementById("galleryCarousel");
const gallerySlides = galleryCarousel.children;
let galleryIndex = 0;

function showGallerySlide(i) {
  galleryIndex = (i + gallerySlides.length) % gallerySlides.length;
  galleryCarousel.style.transform = `translateX(-${galleryIndex * 100}%)`;
}

document.getElementById("prevGallery").addEventListener("click", () => showGallerySlide(galleryIndex - 1));
document.getElementById("nextGallery").addEventListener("click", () => showGallerySlide(galleryIndex + 1));

// Auto-play cada 5s
setInterval(() => showGallerySlide(galleryIndex + 1), 5000);



