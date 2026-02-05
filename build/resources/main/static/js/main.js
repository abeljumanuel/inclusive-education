// main.js — versión robusta y defensiva

// Small helpers
const safeQuery = (sel) => document.querySelector(sel);
const safeGet = (id) => document.getElementById(id);

// Only run DOM-dependent code after DOM is ready
// Only run DOM-dependent code after DOM is ready
document.addEventListener("DOMContentLoaded", () => {
  initMenuToggle();
  initScrollAnimations();

  // Inicializar solo la parte del modal (apertura/cierre)
  initGalleryModalBasic();

  // Cargar datos
  loadTimeline(1);
  loadTimeline(2);
  loadFindings();
  loadInterviews();
  loadAppendices();
  loadGalleryImages(); // Esto inicializará el carousel después de cargar imágenes

  // Global delegation for modal open/close
  initModalDelegation();
});

/* -------------------------
   UI: menu toggle
   ------------------------- */
function initMenuToggle() {
  const menuBtn = safeQuery("#menu-btn");
  const menu = safeQuery("#menu");
  if (!menuBtn || !menu) return;
  menuBtn.addEventListener("click", () => menu.classList.toggle("hidden"));
}

/* -------------------------
   Scroll reveal (light)
   ------------------------- */
function initScrollAnimations() {
  document.addEventListener("scroll", () => {
    document.querySelectorAll("section").forEach((section) => {
      const rect = section.getBoundingClientRect();
      if (rect.top < window.innerHeight * 0.85) {
        section.classList.add("opacity-100", "translate-y-0");
      }
    });
  }, { passive: true });
}

/* -------------------------
   TIMELINE (data-driven)
   ------------------------- */
async function loadTimeline(schoolId) {
  try {
    const resp = await fetch(`/api/inclusive/${schoolId}/timeline`, {
      headers: { "Accept": "application/json" }
    });
    if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
    const timelineDTO = await resp.json();
    renderTimeline(timelineDTO, schoolId);
  } catch (err) {
    console.error("Error loading timeline", err);
  }
}

function renderTimeline(timelineDTO, schoolId) {
  const container = safeGet("timeline-container");
  if (!container) return;

  const color = schoolId === 1 ? "blue" : "green";

  const entriesHtml = (timelineDTO.timelineEntries || []).map(entry => `
    <div>
      <span class="absolute -left-3 w-6 h-6 bg-${color}-500 rounded-full"></span>
      <h4 class="font-bold text-${color}-600">${entry.year} – ${escapeHtml(entry.title)}</h4>
      <p class="text-gray-600">${escapeHtml(entry.description)}</p>
    </div>
  `).join("");

  const html = `
    <div>
      <h3 class="text-xl font-semibold mb-6 text-${color}-700">
        📘 Línea de tiempo – ${escapeHtml(timelineDTO.schoolFullName)} (${escapeHtml(timelineDTO.city)}, ${escapeHtml(timelineDTO.department)})
      </h3>

      <div class="relative border-l-4 border-${color}-500 pl-6 space-y-8">
        ${entriesHtml}
      </div>
    </div>
  `;

  container.insertAdjacentHTML("beforeend", html);
}

/* -------------------------
   FINDINGS (Insights) - dynamic
   ------------------------- */
async function loadFindings() {
  try {
    const resp = await fetch(`/api/inclusive/finding`, {
      headers: { "Accept": "application/json" }
    });
    if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
    const findings = await resp.json();
    renderFindings(findings || []);
  } catch (err) {
    console.error("Error loading findings:", err);
  }
}

function renderFindings(findings) {
  const container = safeGet("findings-container");
  const modalsContainer = safeGet("modals-container");
  if (!container || !modalsContainer) return;

  container.innerHTML = "";
  modalsContainer.innerHTML = "";

  findings.forEach(f => {
    const cardHtml = `
      <button data-modal="modal-${f.id}"
          class="p-6 bg-white rounded-xl shadow hover:shadow-lg transition cursor-pointer">
          ${escapeHtml(f.iconTitle || "")}
          <h3 class="font-semibold mt-2">${escapeHtml(f.title)}</h3>
          <p class="text-sm text-gray-600">${escapeHtml(f.description)}</p>
      </button>
    `;
    const modalHtml = `
      <div id="modal-${f.id}" class="fixed inset-0 bg-black bg-opacity-60 hidden items-center justify-center z-50">
        <div class="bg-white rounded-lg max-w-2xl w-full p-6 relative">
          <button class="absolute top-2 right-2 text-gray-500 hover:text-gray-800" data-close>✖</button>
          <h3 class="text-2xl font-bold mb-4">${escapeHtml(f.modalTitle)}</h3>
          <p class="text-gray-700 mb-4">${escapeHtml(f.modalDescription)}</p>
          <div class="mb-4">
            <img src="${escapeAttr(f.modalImagePath)}" alt="${escapeAttr(f.title)}" class="rounded-lg shadow">
          </div>
        </div>
      </div>
    `;
    container.insertAdjacentHTML("beforeend", cardHtml);
    modalsContainer.insertAdjacentHTML("beforeend", modalHtml);
  });

  // No need to attach individual listeners if using delegation (see initModalDelegation).
}

/* -------------------------
   Modal delegation (open/close) - works with dynamic elements
   ------------------------- */
function initModalDelegation() {
  document.addEventListener("click", (e) => {
    const openBtn = e.target.closest("[data-modal]");
    if (openBtn) {
      const id = openBtn.getAttribute("data-modal");
      const modal = safeGet(id);
      if (modal) {
        modal.classList.remove("hidden");
        modal.classList.add("flex");
      }
      return;
    }

    // Close button inside modal
    const closeBtn = e.target.closest("[data-close]");
    if (closeBtn) {
      const modalRoot = closeBtn.closest("div[id^='modal']");
      if (modalRoot) modalRoot.classList.add("hidden");
      return;
    }

    // Click outside modal content -> close (delegated)
    const clickedModal = e.target.closest("div[id^='modal']");
    if (clickedModal && e.target === clickedModal) {
      clickedModal.classList.add("hidden");
    }
  });
}

/* -------------------------
   CAROUSEL (safe init)
   ------------------------- */
function initCarousels() {
  const carousel = safeGet("carousel");
  const prev = safeGet("prev");
  const next = safeGet("next");
  if (!carousel || !prev || !next) return;

  const slides = carousel.children;   
  if (!slides || slides.length === 0) return;

  //if (!slides || slides.length === 0) {
  //  console.warn("No slides found in interviews carousel");
  //  return;
  //}

  console.log(`Initializing interviews carousel with ${slides.length} slides`);

  let index = 0;
  function showSlide(i) {
    index = (i + slides.length) % slides.length;
    carousel.style.transform = `translateX(-${index * 100}%)`;
  }

  // Remover event listeners previos
  prev.replaceWith(prev.cloneNode(true));
  next.replaceWith(next.cloneNode(true));
  
  // Obtener nuevas referencias
  const newPrev = safeGet("prev");
  const newNext = safeGet("next");

  // Adjuntar nuevos event listeners
  newPrev.addEventListener("click", () => showSlide(index - 1));
  newNext.addEventListener("click", () => showSlide(index + 1));

  // Inicializar posición
  showSlide(0);
  
  setInterval(() => showSlide(index + 1), 6000);
}


// =============================
// Load Interviews (Carousel)
// =============================
async function loadInterviews() {
    try {
        const response = await fetch(`/api/inclusive/interviews`);
        const interviews = await response.json();

        renderInterviews(interviews);

        // IMPORTANTE: después de cargar slides dinámicos
        initCarousels();

    } catch (e) {
        console.error("Error loading interviews", e);
    }
}

function renderInterviews(list) {
    const carousel = document.getElementById("carousel");
    carousel.innerHTML = ""; // limpia carrusel viejo

    list.forEach(item => {
        carousel.innerHTML += `
            <div class="min-w-full px-6">
                <div class="bg-white shadow-md rounded-xl p-6 h-full flex flex-col justify-between">

                    <blockquote class="italic text-lg text-gray-700 mb-4">
                        “${item.quote}”
                    </blockquote>

                    <p class="font-semibold text-blue-700">
                        ${item.intervieweeName}, ${item.intervieweePosition} – ${item.schoolName}
                    </p>

                    <div class="mt-4 flex justify-center gap-4">
                        <a href="${item.urlAudio}" target="_blank"
                            class="bg-blue-600 text-white px-4 py-2 rounded-lg shadow hover:bg-blue-700">
                            🔊 Escuchar
                        </a>
                        <a href="${item.urlFile}" target="_blank"
                            class="bg-gray-200 text-gray-800 px-4 py-2 rounded-lg shadow hover:bg-gray-300">
                            📄 Leer
                        </a>
                    </div>
                </div>
            </div>
        `;
    });
}



/* -------------------------
   GALLERY modal & carousel (delegated)
   ------------------------- */
/* -------------------------
   GALLERY modal basic (solo apertura/cierre)
   ------------------------- */
function initGalleryModalBasic() {
  const galleryModal = safeGet("galleryModal");
  const closeGallery = safeGet("closeGallery");

  if (!galleryModal || !closeGallery) return;

  document.addEventListener("click", (e) => {
    const openBtn = e.target.closest("[data-open-gallery]");
    if (openBtn) {
      e.preventDefault();
      galleryModal.classList.remove("hidden");
      galleryModal.classList.add("flex");
      return;
    }

    if (e.target === closeGallery || e.target === galleryModal) {
      galleryModal.classList.add("hidden");
      return;
    }
  });
}

/* -------------------------
   GALLERY CAROUSEL initialization (llamar después de cargar imágenes)
   ------------------------- */
function initGalleryCarousel() {
  const galleryCarousel = safeGet("galleryCarousel");
  const prevG = safeGet("prevGallery");
  const nextG = safeGet("nextGallery");

  if (!galleryCarousel || !prevG || !nextG) {
    console.error("Gallery carousel elements not found");
    return;
  }

  const gallerySlides = galleryCarousel.children;
  if (!gallerySlides || gallerySlides.length === 0) {
    console.warn("No slides found in gallery carousel");
    return;
  }

  console.log(`Initializing gallery carousel with ${gallerySlides.length} slides`);

  let galleryIndex = 0;

  function showGallerySlide(i) {
    galleryIndex = (i + gallerySlides.length) % gallerySlides.length;
    galleryCarousel.style.transform = `translateX(-${galleryIndex * 100}%)`;
    console.log(`Showing gallery slide ${galleryIndex + 1}/${gallerySlides.length}`);
  }

  // Remover event listeners previos para evitar duplicados
  prevG.replaceWith(prevG.cloneNode(true));
  nextG.replaceWith(nextG.cloneNode(true));
  
  // Obtener las nuevas referencias
  const newPrevG = safeGet("prevGallery");
  const newNextG = safeGet("nextGallery");

  // Adjuntar nuevos event listeners
  newPrevG.addEventListener("click", () => {
    console.log("Previous button clicked");
    showGallerySlide(galleryIndex - 1);
  });
  
  newNextG.addEventListener("click", () => {
    console.log("Next button clicked");
    showGallerySlide(galleryIndex + 1);
  });

  // Inicializar posición
  showGallerySlide(0);

  // Auto-advance (opcional)
  setInterval(() => showGallerySlide(galleryIndex + 1), 5000);
}

/* =============================
    Load Appendices (dynamic)
    ============================= */
async function loadAppendices() {
  try {
    const resp = await fetch(`/api/inclusive/appendices`, {
      headers: { "Accept": "application/json" }
    });
    if (!resp.ok) throw new Error(`HTTP ${resp.status}`);

    const appendices = await resp.json();
    renderAppendices(appendices || []);

  } catch (err) {
    console.error("Error loading appendices:", err);
  }
}

function renderAppendices(list) {
  const container = safeGet("appendices-container");
  if (!container) return;

  container.innerHTML = "";

  list.forEach(a => {

    let actionHtml;

    if (a.functionUrl === "GALLERY") {
      actionHtml = `
        <button data-open-gallery 
                class="mt-4 text-blue-600 font-semibold hover:underline">
          👉 ${escapeHtml(a.functionTitle)}
        </button>
      `;
    } else {
      actionHtml = `
        <a href="${escapeAttr(a.functionUrl)}" target="_blank"
           class="mt-4 text-blue-600 font-semibold hover:underline">
          👉 ${escapeHtml(a.functionTitle)}
        </a>
      `;
    }

    const html = `
      <div class="bg-white p-6 rounded-xl shadow hover:shadow-lg transition flex flex-col">
          <h3 class="text-xl font-semibold mb-2">${escapeHtml(a.iconTitle)} ${escapeHtml(a.title)}</h3>
          
          <p class="text-gray-600 flex-grow">
              ${escapeHtml(a.iconDescription)} ${escapeHtml(a.description)}
          </p>

          ${actionHtml}
      </div>
    `;

    container.insertAdjacentHTML("beforeend", html);
  });
}

/* =============================
    Load Gallery Images (dynamic)
    ============================= */
/* =============================
    Load Gallery Images (dynamic)
    ============================= */
async function loadGalleryImages() {
  try {
    const resp = await fetch(`/api/inclusive/image`, {
      headers: { "Accept": "application/json" }
    });
    if (!resp.ok) throw new Error(`HTTP ${resp.status}`);

    const images = await resp.json();
    renderGalleryImages(images || []);

  } catch (err) {
    console.error("Error loading gallery images:", err);
  }
}

function renderGalleryImages(list) {
  const carousel = safeGet("galleryCarousel");
  if (!carousel) {
    console.error("galleryCarousel element not found");
    return;
  }

  carousel.innerHTML = "";

  list.forEach(img => {
    const slide = `
      <div class="min-w-full flex flex-col items-center">
        
        <!-- Imagen -->
        <img src="gallery/${escapeAttr(img.urlImage)}"
             alt="imagen"
             class="max-h-[70vh] object-contain rounded-lg mb-4">

        <!-- Texto descriptivo -->
        <p class="text-center text-gray-700 px-6 pb-6">
          ${escapeHtml(img.description)}
        </p>

      </div>
    `;
    carousel.insertAdjacentHTML("beforeend", slide);
  });

  console.log(`Loaded ${list.length} gallery images`);

  initGalleryCarousel();
}

/* -------------------------
   Small utilities
   ------------------------- */
// very small escaping helpers to avoid injecting raw HTML / attributes
function escapeHtml(str) {
  if (str == null) return "";
  return String(str).replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll('"', "&quot;")
                    .replaceAll("'", "&#39;");
}
function escapeAttr(str) {
  if (str == null) return "";
  return String(str).replaceAll('"', "&quot;");
}
