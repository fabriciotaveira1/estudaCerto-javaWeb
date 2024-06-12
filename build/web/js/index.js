// -------------------------------- INDEX PAGE ----------------------------------------
//Setting Variables

const p1 = $("#p1");
const p2 = $("#p2");
const p3 = $("#p3");
const p4 = $("#p4");
const p5 = $("#p5");
const p6 = $("#p6");
const p7 = $("#p7");
const screenWidth = window.innerWidth;
//Adding Events

if (screenWidth > 768) {
    p1.on('click', function () {
        p1.hide(200);
    })
    p2.on('click', function () {
        p2.hide(200);
    })
    p3.on('click', function () {
        p3.hide(200);
    })
    p4.on('click', function () {
        p4.hide(200);
    })
    p5.on('click', function () {
        p5.hide(200);
    })
    p6.on('click', function () {
        p6.hide(200);
    })
    p7.on('click', function () {
        p1.show(200);
        p2.show(200);
        p3.show(200);
        p4.show(200);
        p5.show(200);
        p6.show(200);
        p7.hide(200);
    })

}

// MediaQuery for smartphone


//Function to adjust the layout according with the width

if (screenWidth <= 991) {
    //logic for screen less than 767px

    //Enabling the links that are disabled on the media querie for Smartphone
    const btnPage = $("#btnPage");
    const disabled = $(".disabled");
    btnPage.show(0);
    btnPage.on('click', function () {
        if (disabled.css("display") === "none") {
            disabled.css("display", "block");
        } else {
            disabled.css("display", "none");
        }

    })

    p1.on('click', function () {
        p1.hide(200);
        p2.show(200);
    })
    p2.on('click', function () {
        p2.hide(200);
        p3.show(200);
    })
    p3.on('click', function () {
        p3.hide(200);
        p4.show(200);
    })
    p4.on('click', function () {
        p4.hide(200);
        p5.show(200);
    })
    p5.on('click', function () {
        p5.hide(200);
        p6.show(200);
    })
    p6.on('click', function () {
        p6.hide(200);
        p7.show(200);
    })
    p7.on('click', function () {
        p7.hide(200);
        p1.show(200);
    })

}

if (screenWidth >= 992){
    const btnPage = $("#btnPage");
    btnPage.hide(10);
}
    // -------------------------------- INDEX PAGE ----------------------------------------


    // -------------------------------- CHALLENGER PAGE ----------------------------------------
    const row1 = $("#row1");
const row2 = $("#row2");
const row3 = $("#row3");
const row4 = $("#row4");

const card1 = $("#card1");
const card2 = $("#card2");
const card3 = $("#card3");
const card4 = $("#card4");


const cardImg1 = $("#cardImg1");
const cardImg2 = $("#cardImg2");
const cardImg3 = $("#cardImg3");
const cardImg4 = $("#cardImg4");


row1.on("click", function () {
    card1.hide(200);
    if (cardImg1.css("display") === "none") {
        cardImg1.css("display", "block");
    } else {
        card1.show(200);
        cardImg1.css("display", "none")
    }
})
row2.on("click", function () {
    card2.hide(200);
    if (cardImg2.css("display") === "none") {
        cardImg2.css("display", "block");
    } else {
        card2.show(200);
        cardImg2.css("display", "none")
    }
})
row3.on("click", function () {
    card3.hide(200);
    if (cardImg3.css("display") === "none") {
        cardImg3.css("display", "block");
    } else {
        card3.show(200);
        cardImg3.css("display", "none")
    }
})
row4.on("click", function () {
    card4.hide(200);
    if (cardImg4.css("display") === "none") {
        cardImg4.css("display", "block");
    } else {
        card4.show(200);
        cardImg4.css("display", "none")
    }
})
// -------------------------------- CHALLENGER PAGE ----------------------------------------





