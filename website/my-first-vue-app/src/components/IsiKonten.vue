<template>
    <div class="bio">
        <div class="nama2">
            <h2 id="namaBio">Careen Emilza</h2>
        </div>

        <h5 id="userBio">@careenemilza</h5>

        <div class="infoBio">
            <section class="lokasi">
                <img src="/locat.png" alt="loc" class="icon-bio">
                <p>Jawa Timur, Indonesia</p>
            </section>

            <section class="lokasi">
                <img src="/calender.png" alt="cal" class="icon-bio">
                <p>Bergabung, Juni 2024</p>
            </section>
        </div>

        <div class="Follow">
            <section class="foll">
                <h6 id="angka">2k</h6>
                <p>Mengikuti</p>
            </section>

            <section class="foll">
                <h6 id="angka">6k</h6>
                <p>Pengikut</p>
            </section>
        </div>
    </div>
</template>

<script>
export default {
    name: "IsiKonten",
};
</script>

<style scoped>
.bio {
    width: 600px;
    margin-left: 35px;
}

#userBio {
    margin-left: -465px;
    margin-top: 5px;
}

#namaBio {
    font-size: 18px;
}

.nama2 {
    display: flex;
    align-items: center;
    margin-top: -20px;
    margin-left: 20px;
    height: 20px;
}

.infoBio {
    margin-left: 0;
    margin-bottom: -20px;
}

.lokasi {
    display: flex;
    align-items: center;
    gap: 5px;
    margin-left: 20px;
    color: #536471;
    font-size: 14px;
    margin-bottom: -10px;
    margin-top: -10px;
}

.Follow {
    display: flex;
    margin-left: 20px;
    margin-top: 1px;
}

.foll {
    display: flex;
    align-items: center;
    gap: 5px;
    color: #536471;
    font-size: 14px;
    margin-right: 20px; 
}

.icon-bio {
    width: 20px;
    height: 20px;
}

#angka {
    font-size: 15px;
    color: black;
    font-weight: bold;
    margin-left: 1px;
}
</style>