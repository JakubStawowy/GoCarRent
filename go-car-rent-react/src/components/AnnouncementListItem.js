import {Button, Card, Container, ListItemIcon, Typography} from "@material-ui/core";
import image from '../uploads/transit.png';
import DirectionsCarIcon from "@material-ui/icons/DirectionsCar";
import MessageIcon from "@material-ui/icons/Message";
import SettingsIcon from '@material-ui/icons/Settings';
import {NavLink} from "react-router-dom";
import BlockIcon from '@material-ui/icons/Block';
import {useDispatch} from "react-redux";
import {useHistory} from "react-router";
import {useState} from "react";
import LockOpenIcon from '@material-ui/icons/LockOpen';
import AssignmentIcon from '@material-ui/icons/Assignment';
import {blockAnnouncement, sendMessage, unlockAnnouncement} from "../actions/actionRepository";
import {REQUEST_FOR_RENT_CONSENT} from "../data/messageTypes";
import {useAnnouncementListItemStyles} from "../style/AnnouncementListItemStyles";

export default function AnnouncementListItem(props) {

    const dispatch = useDispatch();
    const history = useHistory();
    const [isBlock, setIsBlock] = useState(false);
    const classes = useAnnouncementListItemStyles();

    const changeBlock = () => setIsBlock(!isBlock);
    const handleBlock = () => {
        dispatch(blockAnnouncement(props.announcementId)).then(() => {
            alert('AnnouncementListItem blocked successfully');
            history.replace('/');
        }).catch((error) => {
            alert(error);
        });

    }

    const handleUnlock = () => {
        dispatch(unlockAnnouncement(props.announcementId)).then(() => {
            alert('AnnouncementListItem unlocked successfully');
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
                <Typography variant={"h5"} className={classes.title}>
                    {props.title}
                </Typography>
                <Typography variant={"subtitle2"}>
                    {props.createdAt.replace("T", " ").substr(0, 16)}
                </Typography>
                <NavLink to={'/users/' + props.authorId + '/profile'} className={classes.navLink}>
                    <Typography>
                        Author
                    </Typography>
                </NavLink>
                <Typography variant={'h6'}>
                    {props.price} {props.currency} / {props.timeUnit}
                </Typography>
                    {
                        props.authorId === parseInt(localStorage.getItem("userId")) ?
                            <ListItemIcon>

                                <Button
                                    className={classes.iconButton}
                                    onClick={()=>history.replace("/announcement/" + props.announcementId)}
                                >
                                    <DirectionsCarIcon fontSize={"large"} className={classes.icon}/>
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