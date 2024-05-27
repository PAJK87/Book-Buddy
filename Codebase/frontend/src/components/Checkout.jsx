import React from 'react';
import { PaymentElement, AddressElement } from '@stripe/react-stripe-js';

const CheckoutForm = () => {
  return (
    <form>
        <h3>Card Details</h3>
        <PaymentElement />
        <h3>Shipping Address</h3>
        <AddressElement
            options={{mode: 'shipping', allowedCountries: ['US']}}

            // Access the address like so:
            // onChange={(event) => {
            //   setAddressState(event.value);
            // }}
        />
      <button>Submit</button>
    </form>
  );
};

const Checkout = ({stripePromise}) => {
    const [clientSecret, setClientSecret] = useState(null);
  
    useEffect(() => {
      fetch("/create-payment-intent", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => res.json())
        .then(({clientSecret}) => setClientSecret(clientSecret));
    }, []);
  
    if(clientSecret) {
      return (
        <Elements stripe={stripePromise} options={{clientSecret, appearance}}>
          <PaymentForm />
        </Elements>
      )
    } else {
      return <div>Loading...</div>
    }
  };

export default Checkout;