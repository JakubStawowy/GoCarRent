import {Button, Card, Container, ListItemIcon, makeStyles, Typography} from "@material-ui/core";
import image from '../uploads/transit.png';
import DirectionsCarIcon from "@material-ui/icons/DirectionsCar";
import PhoneEnabledIcon from '@material-ui/icons/PhoneEnabled';
import MessageIcon from "@material-ui/icons/Message";
import SettingsIcon from '@material-ui/icons/Settings';
import {NavLink} from "react-router-dom";
import BlockIcon from '@material-ui/icons/Block';
import {useDispatch} from "react-redux";
import {useHistory} from "react-router";
import {useState} from "react";
import LockOpenIcon from '@material-ui/icons/LockOpen';
import AssignmentIcon from '@material-ui/icons/Assignment';
import {blockAnnouncement, registerRent, sendMessage, unlockAnnouncement} from "../actions/actionRepository";
import {REQUEST_FOR_RENT_CONSENT} from "../data/messageTypes";

const useStyles = makeStyles((theme) => ({
    paper: {
        height: '30vh',
        width: '100%',
        display: 'flex',
        flexDirection: 'row'
    },
    img: {
        height: '90%',
        flex: '3'
    },
    content: {
        height: '100%',
        flex: '7',
        padding: '0',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around'
    },
    p: {
        padding: 0
    },
    cancelButton: {
        background: '#FA8072'
    },
    blockButton: {
        background: '#4BBEBAE0'
    },
    image: {
        width: '15vw',
        height: '15vw',
        objectFit: 'cover'
    }
}));

export default function Announcement(props) {

    const dispatch = useDispatch();
    const history = useHistory();
    const [isBlock, setIsBlock] = useState(false);
    const classes = useStyles();

    const changeBlock = () => setIsBlock(!isBlock);
    const handleBlock = () => {
        dispatch(blockAnnouncement(props.announcementId)).then(() => {
            alert('Announcement blocked successfully');
            history.replace('/');
        }).catch((error) => {
            alert(error);
        });

    }

    const handleUnlock = () => {
        dispatch(unlockAnnouncement(props.announcementId)).then(() => {
            alert('Announcement unlocked successfully');
            history.replace('/');
        }).catch((error) => {
            alert(error);
        });
    }

    const handleRentRequest = () => sendMessage({
        tenantId: localStorage.getItem('userId'),
        announcementId: props.announcementId,
        messageType: REQUEST_FOR_RENT_CONSENT
    }).then(()=>alert("Success!")).catch((error) => {
        alert(error);
    });

    return (
        <Card className={classes.paper}>
            <div className={classes.img}>
                <img src={image} alt={"No-image"} className={classes.image}/>
            </div>
            <Container className={classes.content} style={{padding: '1em'}}>
                <Typography variant={"h5"}>
                    {props.title}
                    <Typography variant={"body1"}>{props.date}</Typography>
                </Typography>
                <NavLink to={'/users/' + props.authorId + '/profile'}>
                    Author
                </NavLink>
                <Typography variant={'h6'}>
                    {props.price} {props.currency} / {props.timeUnit}
                </Typography>
                    {
                        props.authorId === parseInt(localStorage.getItem("userId")) ?
                            <ListItemIcon>

                                <Button>
                                    <DirectionsCarIcon fontSize={"large"}/>
                                </Button>
                                <NavLink to={"/announcement/"+props.announcementId+"/edit"}>
                                    <Button>
                                        <SettingsIcon fontSize={"large"}/>
                                    </Button>
                                </NavLink>
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && props.status !== 'BLOCKED' && !isBlock &&
                                        <Button onClick={() => changeBlock()}>
                                            <BlockIcon fontSize={'large'}/>
                                        </Button>
                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && props.status !== 'BLOCKED' && isBlock &&
                                        <div>
                                            <Button onClick={() => handleBlock()} className={classes.blockButton}>
                                                Confirm
                                            </Button>
                                            <Button onClick={() => changeBlock()} className={classes.cancelButton}>
                                                Cancel
                                            </Button>
                                        </div>
                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && props.status === 'BLOCKED' &&
                                        <Button onClick={() => handleUnlock()}>
                                            <LockOpenIcon fontSize={'large'}/>
                                        </Button>
                                }
                            </ListItemIcon>
                           :
                            <ListItemIcon>
                                <Button>
                                    <DirectionsCarIcon fontSize={"large"}/>
                                </Button>
                                <Button>
                                    <PhoneEnabledIcon fontSize={"large"}/>
                                </Button>
                                <Button>
                                    <MessageIcon fontSize={"large"}/>
                                </Button>
                                {
                                    props.authorId !== parseInt(localStorage.getItem("userId")) &&
                                        <Button onClick={() => handleRentRequest()}>
                                            <AssignmentIcon fontSize={'large'}/>
                                        </Button>

                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && props.status !== 'BLOCKED' && !isBlock &&
                                    <Button onClick={() => changeBlock()}>
                                        <BlockIcon fontSize={'large'}/>
                                    </Button>
                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && props.status !== 'BLOCKED' && isBlock &&
                                    <div>
                                        <Button onClick={() => handleBlock()} className={classes.blockButton}>
                                            Confirm
                                        </Button>
                                        <Button onClick={() => changeBlock()} className={classes.cancelButton}>
                                            Cancel
                                        </Button>
                                    </div>
                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && props.status === 'BLOCKED' &&
                                    <Button onClick={() => handleUnlock()}>
                                        <LockOpenIcon fontSize={'large'}/>
                                    </Button>
                                }

                            </ListItemIcon>
                    }
            </Container>
        </Card>
    )
}