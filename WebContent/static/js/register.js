let registerBtn = document.getElementById("register-btn");
registerBtn.addEventListener("click", () => {
  let id = document.getElementById("login").value;
  let pw = document.getElementById("password").value;

  if (!id.trim() || !pw.trim()) {
    alert("아이디 또는 패스워드를 입력해주세요.");
    return;
  }

  localStorage.setItem(id.trim(), pw.trim());

  alert("회원가입 성공");
  location.href = "login.html";
});
