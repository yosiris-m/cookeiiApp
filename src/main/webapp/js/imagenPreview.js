const fileSelectButton = document.getElementById("fileSelectButton");
const fileInput = document.getElementById("fileInput");
const imgPreview = document.getElementById("imgPreview");

fileInput.addEventListener("change", handleFiles, false);

fileSelectButton.addEventListener(
  "click",
  () => {
    fileInput.click();
  },
  false
);


function handleFiles() {
  
  if (this.files.length === 0) {
    return;
  }
  imgPreview.innerHTML = "";

  const img = document.createElement("img");
  img.src = URL.createObjectURL(this.files[0]);
  img.classList.add("img-pr");
  img.onload = () => {
    URL.revokeObjectURL(img.src);
  };

  imgPreview.appendChild(img);
  const info = document.createElement("span");
  info.classList.add("info");
  info.innerHTML = `${this.files[0].name}: ${this.files[0].size} bytes`;
  imgPreview.appendChild(info);
}


