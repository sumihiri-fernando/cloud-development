// Assuming you have a function to fetch applicants
async function fetchApplicants() {
    try {
        const response = await fetch('/api/applicants'); // Adjust the URL accordingly

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const applicants = await response.json();
        // Process applicants data
    } catch (error) {
        console.error('Error fetching applicants:', error);
        // Handle error (e.g., display a message to the user)
    }
}


document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = 'http://localhost:8080/applicants'; // Adjust as necessary

    // Load applicants on page load
    loadApplicants();

    document.getElementById('addApplicantForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const applicant = {
            name: document.getElementById('name').value,
            birthYear: document.getElementById('birthYear').value,
            email: document.getElementById('email').value,
        };
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(applicant),
        })
            .then(response => response.json())
            .then(data => {
                closeModal();
                loadApplicants();
            })
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('editApplicantForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const applicantId = document.getElementById('editApplicantId').value;
        const applicant = {
            id: applicantId,
            name: document.getElementById('editName').value,
            birthYear: document.getElementById('editBirthYear').value,
            email: document.getElementById('editEmail').value,
        };
        fetch(`${apiUrl}/${applicantId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(applicant),
        })
            .then(response => response.json())
            .then(data => {
                closeEditModal();
                loadApplicants();
            })
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('applicantList').addEventListener('click', function (event) {
        if (event.target.classList.contains('editButton')) {
            const applicantId = event.target.getAttribute('data-id');
            loadApplicantForEdit(applicantId);
        }
        if (event.target.classList.contains('deleteButton')) {
            const applicantId = event.target.getAttribute('data-id');
            deleteApplicant(applicantId);
        }
    });

    document.getElementById('openModal').onclick = function() {
        document.getElementById('applicantModal').style.display = "block";
    }

    document.querySelectorAll('.close').forEach(span => {
        span.onclick = function() {
            closeModal();
            closeEditModal();
        }
    });

    function closeModal() {
        document.getElementById('applicantModal').style.display = "none";
        document.getElementById('addApplicantForm').reset();
    }

    function closeEditModal() {
        document.getElementById('editApplicantModal').style.display = "none";
    }

    function loadApplicants() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(applicants => {
                const applicantList = document.getElementById('applicantList').querySelector('ul');
                applicantList.innerHTML = '';
                applicants.forEach(applicant => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                        <span>${applicant.name} (${applicant.birthYear})</span>
                        <button class="editButton" data-id="${applicant.id}">Edit</button>
                        <button class="deleteButton" data-id="${applicant.id}">Delete</button>
                    `;
                    applicantList.appendChild(li);
                });
            });
    }

    function loadApplicantForEdit(id) {
        fetch(`${apiUrl}/${id}`)
            .then(response => response.json())
            .then(applicant => {
                document.getElementById('editApplicantId').value = applicant.id;
                document.getElementById('editName').value = applicant.name;
                document.getElementById('editBirthYear').value = applicant.birthYear;
                document.getElementById('editEmail').value = applicant.email;
                document.getElementById('editApplicantModal').style.display = "block";
            });
    }

    function deleteApplicant(id) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    loadApplicants();
                } else {
                    console.error('Failed to delete applicant');
                }
            })
            .catch(error => console.error('Error:', error));
    }
});
