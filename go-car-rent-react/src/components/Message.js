import {Button, Card, Container, Typography} from "@material-ui/core";
import {
    REQUEST_FOR_RENT_CONSENT,
    REQUEST_FOR_RENT_REALIZATION, REQUEST_FOR_RENT_RETURN,
    RESPONSE_FOR_RENT_CONSENT,
    RESPONSE_FOR_RENT_REALIZATION, RESPONSE_FOR_RENT_RETURN
} from "../data/messageTypes";
import {archiveMessage, sendMessage, sendRentReturnProcessMessage} from "../actions/actionRepository";
import {useEffect} from "react";
import AirportShuttleIcon from "@material-ui/icons/AirportShuttle";
import {useHistory} from "react-router";
import {useMessageStyles} from "../style/MessageStyles";

export default function Message(props) {

    /*  Hooks   */
    const classes = useMessageStyles();
    const history = useHistory();
    useEffect(() => {
        console.log(props.body);
    }, []);

    const sendRequestForReturn = () => {
        sendRentReturnProcessMessage({
            messageType: REQUEST_FOR_RENT_RETURN,
            tenantId: localStorage.getItem('userId'),
            rentId: props.body.rentId
        }).then(()=> {
            alert("Request for rent return sent successfully");
            props.action();
        }).catch((error)=>alert(error));
    }

    const sendResponseForReturn = (isReturned) => {
        sendRentReturnProcessMessage({
            messageType: RESPONSE_FOR_RENT_RETURN,
            tenantId: props.body.authorId,
            rentId: props.body.rentId,
            isReturned: isReturned
        }).then(()=> {
            alert("Request for rent return sent successfully");
            props.action();
        }).catch((error)=>alert(error));
    }

    const handleOkClick = ()=>props.action();
    const handleAction = (action) => action.then(()=> {
                alert("Success");
                archiveMessage(props.body.messageId).then(()=>props.action()).catch((error) => alert(error));
            }).catch((error)=>alert(error));

    return (
        <Card className={classes.paper}>
            <AirportShuttleIcon fontSize={'large'}/>
            <Container className={classes.content} style={{padding: '1em'}}>
                <Typography>
                    {props.body.sentAt.replace("T", " ").substr(0, 16)}
                </Typography>
                <Typography variant={"h5"}>
                    {
                        props.body.rentMessageType === REQUEST_FOR_RENT_CONSENT && <div>
                            Someone wants to rent a car from you
                            <div>
                                <Button
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={() =>
                                    handleAction(sendMessage({
                                        messageType: RESPONSE_FOR_RENT_CONSENT,
                                        tenantId: props.body.authorId,
                                        announcementId: props.body.announcementId,
                                        consent: true
                                    }))
                                }>Agree</Button>
                                <Button
                                    disabled={props.archived}
                                    className={classes.cancelButton} onClick={() =>
                                    handleAction(sendMessage({
                                        messageType: RESPONSE_FOR_RENT_CONSENT,
                                        tenantId: props.body.authorId,
                                        announcementId: props.body.announcementId,
                                        consent: false
                                    }))
                                }>No, thanks</Button>
                                <Button
                                    className={classes.button}
                                    onClick={()=>history.replace("/users/" + props.body.authorId + "/profile")}
                                >Profile</Button>
                                <Button
                                    className={classes.button}
                                    onClick={()=>history.replace("/announcement/" + props.body.announcementId)}
                                >Announcement</Button>
                            </div>
                        </div>
                    }
                    {
                        props.body.rentMessageType === REQUEST_FOR_RENT_REALIZATION && <div>
                            <Typography>
                                Tenant is ready for rent
                            </Typography>
                            <Button
                                disabled={props.archived}
                                className={classes.okButton}
                                onClick={() => handleAction(sendMessage({
                                        messageType: RESPONSE_FOR_RENT_REALIZATION,
                                        tenantId: props.body.authorId,
                                        announcementId: props.body.announcementId,
                                    }))
                                }>Start rent</Button>
                            <Button onClick={()=>history.replace("/announcement/" + props.body.announcementId)}>
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
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={() => handleAction(sendMessage({
                                            messageType: REQUEST_FOR_RENT_REALIZATION,
                                            tenantId: localStorage.getItem('userId'),
                                            announcementId: props.body.announcementId,
                                        }))
                                }>Rent</Button>
                                <Button onClick={()=>history.replace("/announcement/" + props.body.announcementId)}>
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
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={()=> {
                                        archiveMessage(props.body.messageId).catch((error) => alert(error));
                                        props.action();
                                    }}>
                                    Ok
                                </Button>
                                <Button onClick={()=>history.replace("/announcement/" + props.body.announcementId)}>
                                    Announcement
                                </Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_REALIZATION &&
                            <div>
                                <Typography>
                                    Success! You can now use borrowed car
                                </Typography>
                                <Button
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={()=> {
                                        archiveMessage(props.body.messageId).catch((error) => alert(error));
                                        props.action();
                                    }
                                    }>
                                    Ok
                                </Button>
                                <Button onClick={()=>history.replace("/announcement/" + props.body.announcementId)}>
                                    Announcement
                                </Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === REQUEST_FOR_RENT_RETURN &&
                            <div>
                                <Typography>
                                    Confirm car return
                                </Typography>
                                <Button
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={()=> {
                                    sendResponseForReturn(true);
                                    archiveMessage(props.body.messageId).catch((error) => alert(error));
                                }}>Car was successfully returned</Button>
                                <Button
                                    disabled={props.archived}
                                    className={classes.cancelButton}
                                    onClick={()=> {
                                    sendResponseForReturn(false);
                                    archiveMessage(props.body.messageId).catch((error) => alert(error));
                                }}>Car wasn't returned</Button>

                                <Button onClick={()=>history.replace("/announcement/" + props.body.announcementId)}>
                                    Announcement
                                </Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_RETURN && props.body.flag &&
                            <div>
                                <Typography>
                                    {props.body.isReturned}

                                    Rent was finished successfully. You can now settle accounts with the owner
                                </Typography>
                                <Button
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={()=> {
                                        archiveMessage(props.body.messageId).catch((error) => alert(error));
                                        props.action();
                                    }}>Ok</Button>

                                <Button
                                    className={classes.button}
                                    onClick={()=>history.replace("/users/" + props.body.receiverId + "/profile")}
                                >Owner</Button>
                            </div>
                    }
                    {
                        props.body.rentMessageType === RESPONSE_FOR_RENT_RETURN && !props.body.flag &&
                            <div>
                                <Typography>
                                    Owner claims that you still haven't returned the car
                                </Typography>
                                <Button
                                    disabled={props.archived}
                                    className={classes.okButton}
                                    onClick={()=> {
                                    sendRequestForReturn();
                                    archiveMessage(props.body.messageId).catch((error) => alert(error));
                                }}>Try again</Button>

                                <Button onClick={()=>history.replace("/announcement/" + props.body.announcementId)}>
                                    Announcement
                                </Button>
                            </div>
                    }
                </Typography>
            </Container>
        </Card>
    )
}