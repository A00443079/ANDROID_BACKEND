# AKHIL ROY - A00443079
# 
# -------------------------------------------------------- 
# --------------------------------------------------------
#
Http://androidbackend-env.eba-pmwntcz3.us-east-2.elasticbeanstalk.com/signup
POST method

Request Body 1:
{
    "email": "abc@xyz.com",
    "password": "12345678",
    "confirm_password": "abcdef"
}

Response Body 1:
{
    "success": false,
    "error": "Passwords not matching !",
    "message": null
}



Request Body 2:
{
    "email": "example@example.com",
    "password": "12345678",
    "confirm_password": "12345678"
}

Response Body 2:
{
    "success": false,
    "error": "Email already exists !",
    "message": null
}

Request Body 3:
{
    "email": "abc@xyz.com",
    "password": "12345678",
    "confirm_password": "12345678"
}

Response Body 3:
{
    "success": true,
    "error": null,
    "message": "Successfully Registered"
}
_________________________________________________________________________________________________
Http://androidbackend-env.eba-pmwntcz3.us-east-2.elasticbeanstalk.com/login
POST method

Request Body 1:
{
    "email": "abc@xyz.com",
    "password": "12345678"
}

Response Body 1:
{
    "success": true,
    "error": null,
    "message": "Login Successful !"
}




Request Body 2:
{
    "email": "abc@xyz.com",
    "password": "abcde"
}

Response Body 2:
{
    "success": false,
    "error": "Email or password is Invalid",
    "message": null
}

_________________________________________________________________________________________________
Http://androidbackend-env.eba-pmwntcz3.us-east-2.elasticbeanstalk.com/hotels
GET method


Response Body:
{
    "hotels_list": [
        {
            "hotel_name": "Pearson View",
            "price": 500,
            "availability": true
        },
        {
            "hotel_name": "Street View Motel",
            "price": 20,
            "availability": false
        },
        {
            "hotel_name": "Seasons Inn",
            "price": 600,
            "availability": true
        },
        {
            "hotel_name": "Delta Hotels by Marriott Toronto",
            "price": 600,
            "availability": true
        },
        {
            "hotel_name": "The Westin Harbour Castle",
            "price": 600,
            "availability": true
        },
        {
            "hotel_name": "One King West Hotel and Residence",
            "price": 200,
            "availability": false
        },
        {
            "hotel_name": "Fairmont Royal York Hotel",
            "price": 200,
            "availability": false
        },
        {
            "hotel_name": "Hyatt Regency Toronto",
            "price": 200,
            "availability": false
        }
    ]
}
_________________________________________________________________________________________________
http://androidbackend-env.eba-pmwntcz3.us-east2.elasticbeanstalk.com/bookingConfirmation
POST method

Request Body:
{ 
    "email": "example@example.com",
    "hotel_name": "Fairmont Royal York Hotel",
    "checkin": "2021-08-25",
    "checkout": "2021-08-30",
    "guests_list": [
                        { 
                            "guest_name" : "Tilak Putta",
                            "gender": "Male"
                        },
                        { 
                            "guest_name" : "Siri Chandana",
                            "gender": "Female"
                        }
                        
                    ]
}


Response Body:
{
    "confirmation_number": "3435ae3d-2b24-46cc-89c8-2a06176f974b"
}
_________________________________________________________________________________________________
http://androidbackend-env.eba-pmwntcz3.us-east-2.elasticbeanstalk.com/getReservationDetails?id=3435ae3d-2b24-46cc-89c8-2a06176f974b
GET method


Response Body:
{
    "email": "example@example.com",
    "hotel_name": "Fairmont Royal York Hotel",
    "checkin": "2021-08-25",
    "checkout": "2021-08-30",
    "guests_list": [
        {
            "guest_name": "Tilak Putta",
            "gender": "Male"
        },
        {
            "guest_name": "Siri Chandana",
            "gender": "Female"
        }
    ]
}

_________________________________________________________________________________________________

http://androidbackend-env.eba-pmwntcz3.us-east-2.elasticbeanstalk.com/getListOfConfirmationNumbers?email=putta.tilak@gmail.com
GET method

/getListOfConfirmationNumbers?email=bullshit
Response Body 1:
[]

/getListOfConfirmationNumbers?email=abc@xyz.com
Response Body 2:
[]


/getListOfConfirmationNumbers?email=putta.tilak@gmail.com
Response Body 3:
[
    {
        "confirmation_number": "5b973a54-0b09-45ea-9c44-9618f1862790"
    },
    {
        "confirmation_number": "914a3263-2c48-472f-9228-f4558e554040"
    }
]



/getListOfConfirmationNumbers?email=akhilroycap@gmail.com
Response Body 4:
[
    {
        "confirmation_number": "7c53325e-a844-4083-a375-0fc8c6863b44"
    },
    {
        "confirmation_number": "bcfb5b79-a4fe-4adb-a781-4511ff4c5c26"
    }
]
_________________________________________________________________________________________________

http://androidbackend-env.eba-pmwntcz3.us-east-2.elasticbeanstalk.com/cancel?id=3435ae3d-2b24-46cc-89c8-2a06176f974b

Response Body If does not exist:
{
    "message": "Cancellation Failure !!"
}

Response Body If exists:
{
    "message": "Cancellation Success !!"
}
