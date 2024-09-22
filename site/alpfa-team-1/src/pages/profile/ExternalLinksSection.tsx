import React from 'react';

interface LinkItem {
  label: string;
  inputClassName: string;
}

const ExternalLinksSection: React.FC = () => {
  const linkItems: LinkItem[] = [
    { label: 'Email:', inputClassName: 'h-[33px] w-[422px]' },
    { label: 'Address:', inputClassName: 'h-8 w-[422px]' },
    { label: 'Links:', inputClassName: 'h-[33px] w-[422px]' }
  ];

  return (
    <div className="flex flex-col ml-5 w-6/12 max-md:ml-0 max-md:w-full">
      <div className="flex flex-col px-10 pt-3 pb-11 mx-auto w-full leading-none text-black bg-white rounded-xl font-[number:var(--sds-typography-body-font-weight-regular)] text-[length:var(--sds-typography-body-size-medium)] max-md:px-5 max-md:mt-8 max-md:max-w-full">
        <h3 className="self-center text-3xl font-semibold text-center text-black">External Links</h3>
        <form>
          {linkItems.map((item, index) => (
            <div key={index} className="flex flex-wrap gap-5 justify-between mt-8 whitespace-nowrap max-md:max-w-full">
              <label htmlFor={`link-${index}`} className="my-auto">{item.label}</label>
              <input
                id={`link-${index}`}
                type="text"
                className={`flex shrink-0 max-w-full rounded-xl bg-neutral-100 ${item.inputClassName}`}
                aria-label={item.label}
              />
            </div>
          ))}
          <button
            type="button"
            className="overflow-hidden gap-2 self-center px-3 py-5 mt-14 max-w-full rounded-lg border border-solid bg-zinc-800 border-zinc-800 min-h-[59px] rotate-[0.0007983357746682942rad] text-[color:var(--sds-color-text-brand-on-brand)] w-[249px] max-md:mt-10"
          >
            Edit Links
          </button>
        </form>
      </div>
    </div>
  );
};

export default ExternalLinksSection;