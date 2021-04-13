import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {Button, Container, List, ListItem, makeStyles, Typography} from "@material-ui/core";
import userImage from '../uploads/user.png';
import feedbacks from '../data/feedback';
import Feedback from "../components/Feedback";
import getUserById from "../controllers/UserController";
const useStyles = makeStyles((theme) => ({

    container: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100%'
    },
    img: {
        width: '20%',
        borderRadius: '50%'
    },
    list: {
        maxHeight: '30vh',
        overflow: 'auto',
        width: '100%'
    },
    button: {
        background: '#4BBEBAE0',
        color: 'white'
    }
}));

export default function Profile() {
    const [userData, setUserData] = useState({
        userDetails: {}
    });
    const classes = useStyles();

    useEffect(  () => {
        getUserById(33).then(r => {
            setUserData(r);
        });
    }, []);

    return (
        <Container className={classes.container}>
            <img className={classes.img} src={userImage} alt={''}/>
            <Typography variant={'h3'} gutterBottom={'true'}>
                {`${userData.userDetails.name} ${userData.userDetails.surname}`}
            </Typography>
            <Typography gutterBottom={'true'}>
                <Button variant={'contained'} className={classes.button}>User announcements</Button>
            </Typography>
            <Typography variant={'h4'} gutterBottom={'true'}>
                Feedback
            </Typography>
            <List className={classes.list}>
                {feedbacks.map(
                    feedback => {
                        return (
                            <ListItem>
                                <Feedback
                                    author={feedback.author}
                                    content={feedback.content}
                                    stars={feedback.stars}
                                />
                            </ListItem>
                        )
                    }
                )}
            </List>
        </Container>
    );
}