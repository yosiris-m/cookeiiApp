const photoSelectButton = document.getElementById("fileSelectButton");
const photoInput = document.getElementById("fileInput");
const photoPreview = document.getElementById("imgPreview");

photoInput.addEventListener("change", handleFiles, false);

photoSelectButton.addEventListener(
  "click",
  () => {
    photoInput.click();
  },
  false
);


function handleFiles() {
  
  if (this.files.length === 0) {
    return;
  }
  photoPreview.innerHTML = "";

  const img = document.createElement("img");
  img.src = URL.createObjectURL(this.files[0]);
  img.classList.add("img-pr");
  img.onload = () => {
    URL.revokeObjectURL(img.src);
  };

  photoPreview.appendChild(img);
  const info = document.createElement("span");
  info.classList.add("info");
  info.innerHTML = `${this.files[0].name}: ${this.files[0].size} bytes`;
  photoPreview.appendChild(info);
}


