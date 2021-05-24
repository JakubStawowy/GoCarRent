import {Button, Card, Container, ListItemIcon, makeStyles, Paper, Typography} from "@material-ui/core";
import image from '../uploads/transit.png';
import DirectionsCarIcon from "@material-ui/icons/DirectionsCar";
import PhoneEnabledIcon from '@material-ui/icons/PhoneEnabled';
import MessageIcon from "@material-ui/icons/Message";
import SettingsIcon from '@material-ui/icons/Settings';
import {NavLink} from "react-router-dom";
import BlockIcon from '@material-ui/icons/Block';
import {useDispatch} from "react-redux";
import {blockAnnouncement} from "../actions/blockAnnouncement";
import {useHistory} from "react-router";
import {useEffect, useState} from "react";

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
    }
}));

export default function Announcement(props) {

    const dispatch = useDispatch();
    const history = useHistory();
    const [isBlock, setIsBlock] = useState(false);
    const changeBlock = () => setIsBlock(!isBlock);

    const handleBlock = () => {
        dispatch(blockAnnouncement(props.announcementId)).then(() => {
            alert('Announcement blocked successfully');
            history.replace('/');
        }).catch((error) => {
           alert(error);
        });
    }

    const classes = useStyles();
    return (
        <Card className={classes.paper}>
            <div className={classes.img}>
                <img src={image} alt={"No-image"} style={{width: '100%', height: '100%', objectFit: 'cover'}}/>
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
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && !isBlock &&
                                        <Button onClick={() => changeBlock()}>
                                            <BlockIcon fontSize={'large'}/>
                                        </Button>
                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && isBlock &&
                                        <div>
                                            <Button onClick={() => handleBlock()} className={classes.blockButton}>
                                                Confirm
                                            </Button>
                                            <Button onClick={() => changeBlock()} className={classes.cancelButton}>
                                                Cancel
                                            </Button>
                                        </div>
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
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && !isBlock &&
                                    <Button onClick={() => changeBlock()}>
                                        <BlockIcon fontSize={'large'}/>
                                    </Button>
                                }
                                {
                                    localStorage.getItem("role") === 'ROLE_ADMIN' && isBlock &&
                                    <div>
                                        <Button onClick={() => handleBlock()} className={classes.blockButton}>
                                            Confirm
                                        </Button>
                                        <Button onClick={() => changeBlock()} className={classes.cancelButton}>
                                            Cancel
                                        </Button>
                                    </div>
                                }
                            </ListItemIcon>
                    }
            </Container>
        </Card>
    )
}