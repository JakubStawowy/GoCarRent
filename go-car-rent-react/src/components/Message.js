import {Avatar, Button, Card, Container, makeStyles, Typography} from "@material-ui/core";
import image from '../uploads/user.png';
import {
    REQUEST_FOR_RENT_CONSENT,
    REQUEST_FOR_RENT_REALIZATION, REQUEST_FOR_RENT_RETURN,
    RESPONSE_FOR_RENT_CONSENT,
    RESPONSE_FOR_RENT_REALIZATION, RESPONSE_FOR_RENT_RETURN
} from "../data/messageTypes";
import {deleteMessage, sendMessage, sendRentReturnProcessMessage} from "../actions/actionRepository";
import {useEffect} from "react";
import AirportShuttleIcon from "@material-ui/icons/AirportShuttle";
import {useHistory} from "react-router";

const useStyles = makeStyles((theme) => ({
    paper: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'center',
        padding: '1em'
    },
    img: {
        width: '10vh',
        height: '10vh'
    },
    content: {
        height: '100%',
        padding: '0',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around',
    },
    p: {
        padding: 0
    },
    feedbackTop: {
        display: 'flex',
        justifyContent: 'space-between'
    },
    stars: {
        color: '#4BBEBAE0',
    },
    feedbackAuthorLabel: {

    },
    cancelButton: {
        background: '#FA8072'
    },
    okButton: {
        background: '#4BBEBAE0'
    }
}));


export default function Message(props) {

    /*  Hooks   */
    const classes = useStyles();
    const history = useHistory();
    useEffect(() => {
        console.log(props.body);
    }, []);
    const sendRequestForReturn = () => {
        sendRentReturnProcessMessage({
            messageType: REQUEST_FOR_RENT_RETURN,
            tenantId: localStorage.getItem('userId'),
            rentId: props.body.rentId
        }).then(()=>alert("Request for rent return sent successfully"))
            .catch((error)=>alert(error));
    }

    const sendResponseForReturn = (isReturned) => {
        sendRentReturnProcessMessage({
            messageType: RESPONSE_FOR_RENT_RETURN,
            tenantId: props.body.authorId,
            rentId: props.body.rentId,
            isReturned: isReturned
        }).then(()=>alert("Request for rent return sent successfully"))
            .catch((error)=>alert(error));
    }

    const handleAction = (action) => action.then(()=> {
                alert("Success");
                deleteMessage(props.body.messageId).catch((error) => alert(error));
            }).catch((error)=>alert(error));


    return (
        <Card className={classes.paper}>
            <AirportShuttleIcon fontSize={'large'}/>
            <Container className={classes.content} style={{padding: '1em'}}>
                <Typography variant={"h5"}>
                    {
                        props.body.rentMessageType === REQUEST_FOR_RENT_CONSENT && <div>
                            Someone wants to rent a car from you
                            <div>
                                <Button
                                    className={classes.button}
                                    onClick={()=>history.replace("/users/" + props.body.authorId + "/profile")}
                                >Profile</Button>
                                <Button className={classes.button}>Announcement</Button>
                                <Button  className={classes.okButton} onClick={() =>
                                    handleAction(sendMessage({
                                        messageType: RESPONSE_FOR_RENT_CONSENT,
                                        tenantId: props.body.authorId,
                                        announcementId: props.body.announcementId,
                                        consent: true
                                    }))
                                }>Agree</Button>
                                <Button className={classes.cancelButton} onClick={() =>
                                    handleAction(sendMessage({
                                        messageType: RESPONSE_FOR_RENT_CONSENT,
                                        tenantId: props.body.authorId,
                                        announcementId: props.body.announcementId,
                                        consent: false
                                    }))
                                }>No, thanks</Button>
                            </div>
                        </div>
                    }
                    {
                        props.body.rentMessageType === REQUEST_FOR_RENT_REALIZATION && <div>
                            <Typography>
                                Tenant is ready for rent
                            </Typography>
                            <Button
                                className={classes.okButton}
                                onClick={() => handleAction(sendMessage({
                                        messageType: RESPONSE_FOR_RENT_REALIZATION,
                                        tenantId: props.body.authorId,
                                        announcementId: props.body.announcementId,
                                    }))
                                }>Start rent</Button>
                            <Button>
                                Announcement
                            </Button>
                        </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_CONSENT && props.body.flag &&
                            <div>
                                <Typography>
                                    Success! Owner of announcement agreed for your rent request
                                </Typography>
                                <Button
                                    className={classes.okButton}
                                    onClick={() => handleAction(sendMessage({
                                            messageType: REQUEST_FOR_RENT_REALIZATION,
                                            tenantId: localStorage.getItem('userId'),
                                            announcementId: props.body.announcementId,
                                        }))
                                }>Rent</Button>
                                <Button>
                                    Announcement
                                </Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_CONSENT && !props.body.flag &&
                            <div>
                                <Typography>
                                    Unfortunately, owner is not interested in renting car for you
                                </Typography>
                                <Button
                                    className={classes.okButton}
                                    onClick={()=>deleteMessage(props.body.messageId).catch((error) => alert(error))}>Ok</Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_REALIZATION &&
                            <div>
                                <Typography>
                                    Success! You can now use borrowed car
                                </Typography>
                                <Button
                                    className={classes.okButton}
                                    onClick={()=> deleteMessage(props.body.messageId).catch((error) => alert(error))
                                    }>Ok</Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === REQUEST_FOR_RENT_RETURN &&
                            <div>
                                <Typography>
                                    Confirm car return
                                </Typography>
                                <Button onClick={()=> {
                                    sendResponseForReturn(true);
                                    deleteMessage(props.body.messageId).catch((error) => alert(error));
                                }}>Car was successfully returned</Button>
                                <Button onClick={()=> {
                                    sendResponseForReturn(false);
                                    deleteMessage(props.body.messageId).catch((error) => alert(error));
                                }}>Car wasn't returned</Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_RETURN && props.body.flag &&
                            <div>
                                <Typography>
                                    {props.body.isReturned}

                                    Rent was finished successfully. You can now settle accounts with the owner
                                </Typography>
                                <Button onClick={()=>deleteMessage(props.body.messageId).catch((error) => alert(error))}>Ok</Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_RETURN && !props.body.flag &&
                            <div>
                                <Typography>
                                    Owner claims that you still haven't returned the car
                                </Typography>
                                <Button onClick={()=> {
                                    sendRequestForReturn();
                                    deleteMessage(props.body.messageId).catch((error) => alert(error));
                                }}>Try again</Button>
                            </div>
                    }
                </Typography>
            </Container>
        </Card>
    )
}