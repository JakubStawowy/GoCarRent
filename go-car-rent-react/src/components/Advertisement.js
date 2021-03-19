import {Button, Container, ListItemIcon, makeStyles, Paper, Typography} from "@material-ui/core";
import image from '../uploads/transit.png';
import defaultImage from '../uploads/car.png';
import DirectionsCarIcon from "@material-ui/icons/DirectionsCar";
import PhoneEnabledIcon from '@material-ui/icons/PhoneEnabled';
import MessageIcon from "@material-ui/icons/Message";

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
    }
}));

export default function Advertisement(props) {
    const classes = useStyles();
    return (
        <Paper className={classes.paper}>
            <div className={classes.img}>
                <img src={image} alt={"No-image"} style={{width: '100%', height: '100%', objectFit: 'cover'}}/>
            </div>
            <Container className={classes.content} style={{padding: '1em'}}>
                <Typography variant={"h5"}>
                    {props.title}
                    <Typography variant={"body1"}>{props.date}</Typography>
                </Typography>
                <Typography variant={'h6'}>
                    {props.price}
                </Typography>
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
                </ListItemIcon>
            </Container>
        </Paper>
    )
}