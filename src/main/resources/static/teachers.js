document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = 'http://localhost:8080/api/teachers'; // Adjust as necessary

    // Load teachers on page load
    loadTeachers();

    document.getElementById('addTeacherForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const teacher = {
            name: document.getElementById('name').value,
            subject: document.getElementById('subject').value,
            email: document.getElementById('email').value,
        };
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(teacher),
        })
            .then(response => response.json())
            .then(data => {
                closeModal();
                loadTeachers();
            })
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('editTeacherForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const teacherId = document.getElementById('editTeacherId').value;
        const teacher = {
            id: teacherId,
            name: document.getElementById('editName').value,
            subject: document.getElementById('editSubject').value,
            email: document.getElementById('editEmail').value,
        };
        fetch(`${apiUrl}/${teacherId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(teacher),
        })
            .then(response => response.json())
            .then(data => {
                closeEditModal();
                loadTeachers();
            })
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('teacherList').addEventListener('click', function (event) {
        if (event.target.classList.contains('editButton')) {
            const teacherId = event.target.getAttribute('data-id');
            loadTeacherForEdit(teacherId);
        }
        if (event.target.classList.contains('deleteButton')) {
            const teacherId = event.target.getAttribute('data-id');
            deleteTeacher(teacherId);
        }
    });

    document.getElementById('openModal').onclick = function() {
        document.getElementById('teacherModal').style.display = "block";
    }

    document.querySelectorAll('.close').forEach(span => {
        span.onclick = function() {
            closeModal();
            closeEditModal();
        }
    });

    function closeModal() {
        document.getElementById('teacherModal').style.display = "none";
        document.getElementById('addTeacherForm').reset();
    }

    function closeEditModal() {
        document.getElementById('editTeacherModal').style.display = "none";
    }

    function loadTeachers() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(teachers => {
                const teacherList = document.getElementById('teacherList').querySelector('ul');
                teacherList.innerHTML = '';
                teachers.forEach(teacher => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                        <span>${teacher.name}</span>
                        <button class="editButton" data-id="${teacher.id}">Edit</button>
                        <button class="deleteButton" data-id="${teacher.id}">Delete</button>
                    `;
                    teacherList.appendChild(li);
                });
            });
    }

    function loadTeacherForEdit(id) {
        fetch(`${apiUrl}/${id}`)
            .then(response => response.json())
            .then(teacher => {
                document.getElementById('editTeacherId').value = teacher.id;
                document.getElementById('editName').value = teacher.name;
                document.getElementById('editSubject').value = teacher.subject;
                document.getElementById('editEmail').value = teacher.email;
                document.getElementById('editTeacherModal').style.display = "block";
            });
    }

    function deleteTeacher(id) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    loadTeachers();
                } else {
                    console.error('Failed to delete teacher');
                }
            })
            .catch(error => console.error('Error:', error));
    }
});
